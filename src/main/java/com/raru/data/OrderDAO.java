package com.raru.data;

import java.util.Arrays;
import java.util.List;

import org.json.JSONObject;

import com.raru.model.Order;
import com.raru.utils.QueryBuilder;

public class OrderDAO extends AbstractDAO<Order> {
    @Override
    public Order create(Order order) throws DBException {
        String query = QueryBuilder.relate(order.getClient(), "orders", order.getProduct());
        JSONObject result = DataBase.run(query).getJSONObject(0);
        order.setId(result.getString("id"));
        order.setClient(result.getString("in"));
        order.setProduct(result.getString("out"));
        return order;
    }

    @Override
    public Order findUnique(String id) throws DBException {
        var f = Arrays.asList("id", "out as product", "in as client");
        return _findUnique(id, f.toArray(new String[0]));
    }

    @Override
    public List<Order> findMany() throws DBException {
        var f = Arrays.asList("id", "out as product", "in as client");
        return _findMany(f.toArray(new String[0]));
    }

    @Override
    public Order update(Order order) {
        throw new UnsupportedOperationException("Cannot update an order");
    }

    @Override
    public void delete(String id) {
        throw new UnsupportedOperationException("Cannot delete an order");
    }

    @Override
    public String getTableName() {
        return "orders";
    }
}
