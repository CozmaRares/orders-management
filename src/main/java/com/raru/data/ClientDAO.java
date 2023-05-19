package com.raru.data;

import java.util.List;

import org.json.JSONArray;

import com.raru.model.Client;
import com.raru.model.Product;
import com.raru.utils.QueryBuilder;

public class ClientDAO extends AbstractDAO<Client> {
    /**
     * Retrieves a list of ordered products based on the given ID.
     * 
     * @param id The ID of the order.
     * @return The list of ordered products.
     * @throws DBException If an error occurs while accessing the database.
     */
    public List<Product> getOrderedProducts(String id) throws DBException {
        String query = QueryBuilder.selectFetch("->orders->Product", id, "products");
        JSONArray result = DataBase.run(query).getJSONObject(0).getJSONArray("products");
        return new ProductDAO().parseMultipleResults(result);
    }
}
