package com.raru.utils;

import java.lang.reflect.Field;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Utils {
    private Utils() {
    }

    public static void catchHelper(Throwable e) {
        e.printStackTrace();
        System.exit(1);
    }

    /**
     * Retrieves the field values of an object and returns them as a map.
     * 
     * @param obj the object from which to retrieve field values
     * @return a map containing the field names and their corresponding values
     */
    public static Map<String, Object> getFieldValues(Object obj) {
        Class<?> clazz = obj.getClass();
        var values = new HashMap<String, Object>();
        Field[] fields = clazz.getDeclaredFields();

        try {
            for (Field f : fields) {
                f.setAccessible(true);
                String fieldName = f.getName();
                Object value = f.get(obj);

                if (value != null && !fieldName.contains("Date"))
                    values.put(fieldName, value);
            }
        } catch (IllegalAccessException e) {
            catchHelper(e);
        }

        values.remove("id");
        return values;
    }

    /**
     * Formats the display value of a given object based on its field type.
     * 
     * @param value     the value to be formatted
     * @param fieldType the type of the field
     * @return the formatted display value as a string
     */
    @SuppressWarnings("unchecked")
    private static String formatValueDisplay(Object value, String fieldType) {
        if (fieldType.equals("Date")) {
            var fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");
            return fmt.format(((Date) value).getDate());
        }

        if (fieldType.equals("List")) {
            var values = (List<Object>) value;
            return values.size() == 0
                    ? "none"
                    : String.join(", ", values.toArray(new String[0]));
        }

        return value.toString();
    }

    /**
     * Retrieves the field values of an object and returns them as an array of
     * strings.
     * 
     * @param obj the object from which to retrieve field values
     * @return an array containing the field values as strings
     */
    public static String[] getFieldValuesArray(Object obj) {
        Class<?> clazz = obj.getClass();
        Field[] fields = clazz.getDeclaredFields();
        var values = new ArrayList<String>();

        try {
            for (Field f : fields) {
                f.setAccessible(true);
                String fieldType = f.getType().getSimpleName();
                String value = formatValueDisplay(f.get(obj), fieldType);

                if (value != null)
                    values.add(value.toString());
            }
        } catch (IllegalAccessException e) {
            catchHelper(e);
        }

        return values.toArray(new String[0]);
    }

    /**
     * Retrieves the ID value of an object.
     * 
     * @param obj the object from which to retrieve the ID value
     * @return the ID value as a string
     */
    public static String getId(Object obj) {
        try {
            Field idField = obj.getClass().getDeclaredField("id");
            idField.setAccessible(true);
            return idField.get(obj).toString();
        } catch (Exception e) {
            Utils.catchHelper(e);
            return null;
        }
    }

    /**
     * Formats a map of key-value pairs as a JSON string.
     * 
     * @param values the map of key-value pairs to be formatted
     * @return the formatted JSON string representation
     */
    public static String JSONFormat(Map<String, Object> values) {
        boolean needsComma = false;

        var sb = new StringBuilder("{ ");

        for (var entry : values.entrySet()) {
            if (needsComma)
                sb.append(", ");
            needsComma = true;

            String value = entry.getValue().toString();

            if (!(entry.getValue() instanceof Collection))
                value = "\"" + value + "\"";

            sb.append(entry.getKey()).append(": ").append(value);
        }

        return sb.append(" }").toString();
    }

    public static String capitalize(String s) {
        return s.substring(0, 1).toUpperCase() + s.substring(1);
    }
}
