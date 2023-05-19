package com.raru.data;

import org.json.JSONObject;

import com.raru.model.Bill;
import com.raru.utils.Date;

public class BillDAO extends AbstractDAO<Bill> {
    /**
     * Converts a duration string to a Date object by executing a database query.
     *
     * @param duration The duration string to convert.
     * @return The converted Date object.
     * @throws DBException If an error occurs while accessing the database or
     *                     parsing the result.
     */
    public static Date fromDuration(String duration) throws DBException {
        String date = DataBase.run("SELECT * FROM time::now() + " + duration + ";").getString(0);
        return new Date(date);
    }

    @Override
    public Bill update(Bill bill) {
        throw new UnsupportedOperationException("Cannot update a bill");
    }

    @Override
    public void delete(String id) {
        throw new UnsupportedOperationException("Cannot delete a bill");
    }

    @Override
    public String getTableName() {
        return "Log";
    }

    @Override
    protected Bill createObject(JSONObject obj) {
        String id = obj.getString("id");
        String order_ = obj.getString("order_");
        Date dueDate = new Date(obj.getString("dueUntil"));
        Date createDate = new Date(obj.getString("createDate"));

        return new Bill(id, order_, dueDate, createDate);
    }
}
