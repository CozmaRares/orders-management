package com.raru.logic;

import java.util.List;

import com.raru.data.BillDAO;
import com.raru.data.DBException;
import com.raru.model.Bill;
import com.raru.utils.Date;

public class BillLogic {
    private static BillDAO dao = new BillDAO();

    /**
     * Converts a duration string to a Date object.
     *
     * @param duration The duration string to convert.
     * @return The converted Date object.
     * @throws DBException If an error occurs while accessing the database.
     */
    public static Date fromDuration(String duration) throws DBException {
        return BillDAO.fromDuration(duration);
    }

    private BillLogic() {
    }

    /**
     * Creates a new Bill with the specified order ID and due date.
     *
     * @param orderID  The ID of the associated order.
     * @param dueUntil The due date of the bill.
     * @return The created Bill.
     * @throws DBException If an error occurs while accessing the database.
     */
    public static Bill create(String orderID, Date dueUntil) throws DBException {
        return dao.create(new Bill(orderID, dueUntil));
    }

    /**
     * Finds a unique Bill by the specified ID.
     *
     * @param id The ID of the bill to find.
     * @return The found Bill.
     * @throws DBException If an error occurs while accessing the database or if no
     *                     bill is found with the given ID.
     */
    public static Bill findUnique(String id) throws DBException {
        var bill = dao.findUnique(id);

        if (bill == null)
            throw new DBException("No bill with id: " + id);

        return bill;
    }

    /**
     * Retrieves a list of all Bills.
     *
     * @return A list of all Bills.
     * @throws DBException If an error occurs while accessing the database.
     */
    public static List<Bill> findMany() throws DBException {
        return dao.findMany();
    }
}
