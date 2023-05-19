package com.raru.data;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.raru.utils.Date;
import com.raru.utils.QueryBuilder;
import com.raru.utils.Utils;

public abstract class AbstractDAO<T> {
    protected final Class<T> type;
    private final Constructor<T> ctor;

    @SuppressWarnings("unchecked")
    protected AbstractDAO() {
        this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

        Constructor<T>[] ctors = (Constructor<T>[]) type.getDeclaredConstructors();
        Constructor<T> ctor = null;
        for (var c : ctors)
            if (c.getParameterCount() == 0) {
                ctor = c;
                break;
            }

        if (ctor == null)
            throw new RuntimeException("Cannot find default constructor for " + this.getTableName());

        this.ctor = ctor;
    }

    public Constructor<T> getGenericConstructor() {
        return this.ctor;
    }

    public Class<T> getGenericType() {
        return this.type;
    }

    public String getTableName() {
        return this.type.getSimpleName();
    }

    /**
     * Creates a new object in the database by executing the corresponding query.
     *
     * @param obj The object to create.
     * @return The created object.
     * @throws DBException If an error occurs while accessing the database or
     *                     parsing the result.
     */
    public T create(T obj) throws DBException {
        String query = QueryBuilder.create(obj, getTableName());
        JSONArray result = DataBase.run(query);
        return parseSingleResult(result);
    }

    /**
     * Retrieves a unique object from the database based on the given ID and fields.
     *
     * @param id     The ID of the object to retrieve.
     * @param fields The fields to select.
     * @return The retrieved object.
     * @throws DBException If an error occurs while accessing the database or
     *                     parsing the result.
     */
    protected T _findUnique(String id, String... fields) throws DBException {
        String query = QueryBuilder.select(id, fields);
        JSONArray result = DataBase.run(query);
        return parseSingleResult(result);
    }

    public T findUnique(String id) throws DBException {
        return _findUnique(id);
    }

    /**
     * Retrieves multiple objects from the database based on the given fields.
     *
     * @param fields The fields to select.
     * @return The retrieved objects.
     * @throws DBException If an error occurs while accessing the database or
     *                     parsing the result.
     */
    protected List<T> _findMany(String... fields) throws DBException {
        String query = QueryBuilder.select(getTableName(), fields);
        JSONArray result = DataBase.run(query);
        return parseMultipleResults(result);
    }

    public List<T> findMany() throws DBException {
        return _findMany();
    }

    /**
     * Updates an object in the database by executing the corresponding query.
     *
     * @param obj The object to update.
     * @return The updated object.
     * @throws DBException If an error occurs while accessing the database or
     *                     parsing the result.
     */
    public T update(T obj) throws DBException {
        String query = QueryBuilder.update(obj);
        JSONArray result = DataBase.run(query);
        return parseSingleResult(result);
    }

    /**
     * Deletes an object from the database based on the given ID.
     *
     * @param id The ID of the object to delete.
     * @throws DBException If an error occurs while accessing the database.
     */
    public void delete(String id) throws DBException {
        DataBase.run(QueryBuilder.delete(id));
    }

    /**
     * Parses a single JSON result into an object of type T.
     *
     * @param arr The JSON array containing the result.
     * @return The parsed object.
     */
    T parseSingleResult(JSONArray arr) {
        if (arr.length() == 0)
            return null;

        return createObject(arr.getJSONObject(0));
    }

    /**
     * Parses multiple JSON results into a list of objects of type T.
     *
     * @param arr The JSON array containing the results.
     * @return The parsed list of objects.
     */
    List<T> parseMultipleResults(JSONArray arr) {
        int size = arr.length();
        var res = new ArrayList<T>(size);

        for (int i = 0; i < size; i++)
            res.add(createObject(arr.getJSONObject(i)));

        return res;
    }

    /**
     * Formats a value based on its field type.
     *
     * @param fieldType The field type.
     * @param value     The value to format.
     * @return The formatted value.
     */
    protected Object formatValue(String fieldType, Object value) {
        if (value == JSONObject.NULL)
            return null;

        if (fieldType.equals("List")) {
            JSONArray arr = (JSONArray) value;
            var result = new ArrayList<Object>();
            int size = arr.length();

            for (int i = 0; i < size; i++)
                result.add(arr.get(i));

            return result;
        }

        if (fieldType.equals("Date"))
            return new Date(value.toString());

        if (fieldType.equals("Double")) {
            String val = value.toString();
            return Double.parseDouble(val);
        }

        return value;
    }

    /**
     * Creates an object of type T from a JSON object.
     *
     * @param obj The JSON object containing the data.
     * @return The created object.
     */
    protected T createObject(JSONObject obj) {
        T instance = null;

        try {
            ctor.setAccessible(true);
            instance = (T) ctor.newInstance();
            for (Field field : type.getDeclaredFields()) {
                String fieldName = field.getName();
                String fieldType = field.getType().getSimpleName();
                Object value = formatValue(fieldType, obj.get(fieldName));
                PropertyDescriptor propertyDescriptor = new PropertyDescriptor(fieldName, type);
                Method method = propertyDescriptor.getWriteMethod();
                method.invoke(instance, value);
            }
        } catch (Exception e) {
            Utils.catchHelper(e);
        }

        return instance;
    }
}
