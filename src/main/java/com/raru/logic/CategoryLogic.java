package com.raru.logic;

import java.util.List;

import com.raru.data.CategoryDAO;
import com.raru.data.DBException;
import com.raru.model.Category;

public class CategoryLogic {
    private static CategoryDAO dao = new CategoryDAO();

    private CategoryLogic() {
    }

    /**
     * Creates a new Category with the specified name.
     *
     * @param name The name of the category.
     * @return The created Category.
     * @throws DBException If an error occurs while accessing the database.
     */
    public static Category create(String name) throws DBException {
        return dao.create(new Category(name));
    }

    /**
     * Finds a unique Category by the specified id.
     *
     * @param id The id of the category to find.
     * @return The found Category.
     * @throws DBException If an error occurs while accessing the database or if no
     *                     category is found with the given id.
     */
    public static Category findUnique(String id) throws DBException {
        var category = dao.findUnique(id);

        if (category == null)
            throw new DBException("No category with id: " + id);

        return category;
    }

    /**
     * Retrieves a list of all Categories.
     *
     * @return A list of all Categories.
     * @throws DBException If an error occurs while accessing the database.
     */
    public static List<Category> findMany() throws DBException {
        return dao.findMany();
    }

    /**
     * Updates the specified Category.
     *
     * @param category The Category to update.
     * @return The updated Category.
     * @throws DBException If an error occurs while accessing the database.
     */
    public static Category update(Category category) throws DBException {
        return dao.update(category);
    }

    /**
     * Deletes a Category by the specified id.
     *
     * @param id The id of the category to delete.
     * @throws DBException If an error occurs while accessing the database.
     */
    public static void delete(String id) throws DBException {
        dao.delete(id);
    }

    /**
     * Adds a product to the specified Category.
     *
     * @param categoryID The id of the category.
     * @param productID  The id of the product to add.
     * @return The updated Category.
     * @throws DBException If an error occurs while accessing the database.
     */
    public static Category addProduct(String categoryID, String productID) throws DBException {
        return dao.addProduct(categoryID, productID);
    }
}
