package com.raru.model;

import java.util.List;

import org.json.JSONObject;

import com.raru.utils.Date;

public class Product {
    private String id;
    private String name;
    private String brand;
    private List<String> categories;
    private Double price;
    private Integer quantity;
    private Date createDate;
    private Date modifyDate;

    @SuppressWarnings("unused")
    private Product() {
        this.id = null;
        this.name = null;
        this.brand = null;
        this.categories = null;
        this.price = null;
        this.quantity = null;
        this.createDate = null;
        this.modifyDate = null;
    }

    public Product(String name, String brand, double price, int quantity) {
        this.name = name;
        this.brand = brand;
        this.price = price;
        this.quantity = quantity;
        this.id = null;
        this.categories = null;
        this.createDate = null;
        this.modifyDate = null;
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

    public String getBrand() {
        return this.brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public List<String> getCategories() {
        return this.categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Date getCreateDate() {
        return this.createDate;
    }

    public void setCreateDate(Date createdAt) {
        this.createDate = createdAt;
    }

    public Date getModifyDate() {
        return this.modifyDate;
    }

    public void setModifyDate(Date modifiedAt) {
        this.modifyDate = modifiedAt;
    }

    @Override
    public String toString() {
        return "Product: " + new JSONObject(this).toString();
    }
}
