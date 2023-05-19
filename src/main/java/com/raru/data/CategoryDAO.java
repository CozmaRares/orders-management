package com.raru.data;

import java.util.List;

import org.json.JSONArray;

import com.raru.model.Category;
import com.raru.model.Product;

public class CategoryDAO extends AbstractDAO<Category> {
    @Override
    public void delete(String id) throws DBException {
        var dao = new ProductDAO();

        List<Product> products = dao.findMany();

        for (var product : products) {
            List<String> categories = product.getCategories();

            if (categories.size() == 0)
                break;

            categories.removeIf(c -> c.equals(id));
            dao.update(product);
        }

        super.delete(id);
    }

    /**
     * Adds a product to a category by updating the database with the corresponding
     * query.
     *
     * @param categoryID The ID of the category to which the product will be added.
     * @param productID  The ID of the product to add to the category.
     * @return The updated Category object.
     * @throws DBException If an error occurs while accessing the database or
     *                     parsing the result.
     */
    public Category addProduct(String categoryID, String productID) throws DBException {
        String query = String.format(
                "UPDATE %s SET products = array::add(products, %s); UPDATE %s SET categories = array::add(categories, %s);",
                categoryID, productID, productID, categoryID);
        JSONArray result = DataBase.run(query);
        return parseSingleResult(result);
    }
}
