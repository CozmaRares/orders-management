package com.raru.model;

import java.util.List;

import org.json.JSONObject;

public class Category {
    private String id;
    private String name;
    private List<String> products;

    @SuppressWarnings("unused")
    private Category() {
        this.id = null;
        this.name = null;
        this.products = null;
    }

    public Category(String name) {
        this.name = name;
        this.id = null;
        this.products = null;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getProducts() {
        return this.products;
    }

    public void setProducts(List<String> products) {
        this.products = products;
    }

    public void addProduct(String productID) {
        this.products.add(productID);
    }

    @Override
    public String toString() {
        return "Category: " + new JSONObject(this).toString();
    }
}
