package com.raru.logic;

import java.util.List;

import com.raru.data.ClientDAO;
import com.raru.data.DBException;
import com.raru.logic.validators.EmailValidator;
import com.raru.logic.validators.EmailValidator.InvalidEmailException;
import com.raru.model.Client;
import com.raru.model.Product;

public class ClientLogic {
    private static ClientDAO dao = new ClientDAO();

    private ClientLogic() {
    }

    /**
     * Creates a new Client with the specified name and email.
     *
     * @param name  The name of the client.
     * @param email The email of the client.
     * @return The created Client.
     * @throws InvalidEmailException If the email is invalid.
     * @throws DBException           If an error occurs while accessing the
     *                               database.
     */
    public static Client create(String name, String email) throws InvalidEmailException, DBException {
        EmailValidator.validate(email);
        return dao.create(new Client(name, email));
    }

    /**
     * Finds a unique Client by the specified id.
     *
     * @param id The id of the client to find.
     * @return The found Client.
     * @throws DBException If an error occurs while accessing the database or if no
     *                     client is found with the given id.
     */
    public static Client findUnique(String id) throws DBException {
        var client = dao.findUnique(id);

        if (client == null)
            throw new DBException("No client with id: " + id);

        return client;
    }

    /**
     * Retrieves a list of all Clients.
     *
     * @return A list of all Clients.
     * @throws DBException If an error occurs while accessing the database.
     */
    public static List<Client> findMany() throws DBException {
        return dao.findMany();
    }

    /**
     * Updates the specified Client.
     *
     * @param client The Client to update.
     * @return The updated Client.
     * @throws DBException If an error occurs while accessing the database.
     */
    public static Client update(Client client) throws DBException {
        return dao.update(client);
    }

    /**
     * Deletes a Client by the specified id.
     *
     * @param id The id of the client to delete.
     * @throws DBException If an error occurs while accessing the database.
     */
    public static void delete(String id) throws DBException {
        dao.delete(id);
    }

    /**
     * Retrieves a list of ordered products based on the given ID.
     * 
     * @param id The ID of the order.
     * @return The list of ordered products.
     * @throws DBException If an error occurs while accessing the database.
     */
    public static List<Product> getOrderedProducts(String id) throws DBException {
        return dao.getOrderedProducts(id);
    }
}
