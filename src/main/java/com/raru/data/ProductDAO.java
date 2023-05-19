package com.raru.data;

import java.util.List;

import org.json.JSONArray;

import com.raru.model.Category;
import com.raru.model.Client;
import com.raru.model.Product;
import com.raru.utils.QueryBuilder;

public class ProductDAO extends AbstractDAO<Product> {
    @Override
    public void delete(String id) throws DBException {
        var dao = new CategoryDAO();

        List<Category> categories = dao.findMany();

        for (var category : categories) {
            List<String> products = category.getProducts();

            if (products.size() == 0)
                break;

            products.removeIf(p -> p.equals(id));
            dao.update(category);
        }

        super.delete(id);
    }

    /**
     * Retrieves a list of clients who have placed orders for the product with the
     * given ID.
     * 
     * @param id The ID of the product.
     * @return The list of clients.
     * @throws DBException If an error occurs while accessing the database.
     */
    public List<Client> getOrderedBy(String id) throws DBException {
        String query = QueryBuilder.selectFetch("<-orders<-Client", id, "clients");
        JSONArray result = DataBase.run(query).getJSONObject(0).getJSONArray("clients");
        return new ClientDAO().parseMultipleResults(result);
    }
}
