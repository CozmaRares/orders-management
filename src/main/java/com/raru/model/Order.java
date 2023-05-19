package com.raru.model;

import org.json.JSONObject;

public class Order {
    private String id;
    private String client;
    private String product;

    @SuppressWarnings("unused")
    private Order() {
        this.id = null;
        this.client = null;
        this.product = null;
    }

    public Order(String clientID, String productID) {
        this.client = clientID;
        this.product = productID;
        this.id = null;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClient() {
        return this.client;
    }

    public void setClient(String clientID) {
        this.client = clientID;
    }

    public String getProduct() {
        return this.product;
    }

    public void setProduct(String productID) {
        this.product = productID;
    }

    @Override
    public String toString() {
        return "Order: " + new JSONObject(this).toString();
    }
}
