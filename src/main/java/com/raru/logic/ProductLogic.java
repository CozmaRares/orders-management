package com.raru.logic;

import java.util.List;

import com.raru.data.ProductDAO;
import com.raru.logic.validators.PriceValidator;
import com.raru.logic.validators.QuantityValidator;
import com.raru.logic.validators.PriceValidator.InvalidPriceException;
import com.raru.logic.validators.QuantityValidator.InvalidQuantityException;
import com.raru.data.DBException;
import com.raru.model.Client;
import com.raru.model.Product;

public class ProductLogic {
    private static ProductDAO dao = new ProductDAO();

    private ProductLogic() {
    }

    /**
     * Creates a new Product with the specified name, brand, and price.
     *
     * @param name  The name of the product.
     * @param brand The brand of the product.
     * @param price The price of the product.
     * @return The created Product.
     * @throws InvalidPriceException    If the price is invalid.
     * @throws InvalidQuantityException If the quantity is invalid.
     * @throws DBException              If an error occurs while accessing the
     *                                  database.
     */
    public static Product create(String name, String brand, double price, int quantity)
            throws InvalidPriceException, InvalidQuantityException, DBException {
        PriceValidator.validate(price);
        QuantityValidator.validate(quantity);

        return dao.create(new Product(name, brand, price, quantity));
    }

    /**
     * Finds a unique Product by the specified id.
     *
     * @param id The id of the product to find.
     * @return The found Product.
     * @throws DBException If an error occurs while accessing the database or if no
     *                     product is found with the given id.
     */
    public static Product findUnique(String id) throws DBException {
        var product = dao.findUnique(id);

        if (product == null)
            throw new DBException("No product with id: " + id);

        return product;
    }

    /**
     * Retrieves a list of all Products.
     *
     * @return A list of all Products.
     * @throws DBException If an error occurs while accessing the database.
     */
    public static List<Product> findMany() throws DBException {
        return dao.findMany();
    }

    /**
     * Updates the specified Product.
     *
     * @param product The Product to update.
     * @return The updated Product.
     * @throws DBException If an error occurs while accessing the database.
     */
    public static Product update(Product product) throws DBException {
        return dao.update(product);
    }

    /**
     * Deletes a Product by the specified id.
     *
     * @param id The id of the product to delete.
     * @throws DBException If an error occurs while accessing the database.
     */
    public static void delete(String id) throws DBException {
        dao.delete(id);
    }

    /**
     * Retrieves a list of clients who have placed orders for the product with the
     * given ID.
     * 
     * @param id The ID of the product.
     * @return The list of clients.
     * @throws DBException If an error occurs while accessing the database.
     */
    public static List<Client> getOrderedBy(String id) throws DBException {
        return dao.getOrderedBy(id);
    }
}
