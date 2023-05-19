package com.raru.logic;

import java.util.List;

import com.raru.data.OrderDAO;
import com.raru.logic.validators.DurationValidator;
import com.raru.logic.validators.DurationValidator.InvalidDurationException;
import com.raru.data.BillDAO;
import com.raru.data.DBException;
import com.raru.model.Order;
import com.raru.utils.Date;

public class OrderLogic {
    private static OrderDAO dao = new OrderDAO();

    private OrderLogic() {
    }

    /**
     * Creates a new Order with the specified client ID, product ID, and due until
     * duration.
     *
     * @param clientID         The ID of the client for the order.
     * @param productID        The ID of the product for the order.
     * @param dueUntilDuration The duration until which the order is due.
     * @return The created Order.
     * @throws InvalidDurationException If the due until duration is invalid.
     * @throws DBException              If an error occurs while accessing the
     *                                  database.
     */
    public static Order create(String clientID, String productID, String dueUntilDuration)
            throws InvalidDurationException, DBException {
        DurationValidator.validate(dueUntilDuration);
        Date dueUntil = BillDAO.fromDuration(dueUntilDuration);
        Order o = dao.create(new Order(clientID, productID));
        BillLogic.create(o.getId(), dueUntil);
        return o;
    }

    /**
     * Finds a unique Order by the specified ID.
     *
     * @param id The ID of the order to find.
     * @return The found Order.
     * @throws DBException If an error occurs while accessing the database or if no
     *                     order is found with the given ID.
     */
    public static Order findUnique(String id) throws DBException {
        var order = dao.findUnique(id);

        if (order == null)
            throw new DBException("No order with id: " + id);

        return order;
    }

    /**
     * Retrieves a list of all Orders.
     *
     * @return A list of all Orders.
     * @throws DBException If an error occurs while accessing the database.
     */
    public static List<Order> findMany() throws DBException {
        return dao.findMany();
    }

    /**
     * Updates the specified Order.
     *
     * @param order The Order to update.
     * @return The updated Order.
     * @throws DBException If an error occurs while accessing the database.
     */
    public static Order update(Order order) throws DBException {
        return dao.update(order);
    }

    /**
     * Deletes an Order by the specified ID.
     *
     * @param id The ID of the order to delete.
     * @throws DBException If an error occurs while accessing the database.
     */
    public static void delete(String id) throws DBException {
        dao.delete(id);
    }
}
