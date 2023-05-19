package com.raru.model;

import org.json.JSONObject;

import com.raru.utils.Date;

public class Client {
    private String id;
    private String name;
    private String email;
    private Date registerDate;

    @SuppressWarnings("unused")
    private Client() {
        this.id = null;
        this.name = null;
        this.email = null;
        this.registerDate = null;
    }

    public Client(String name, String email) {
        this.name = name;
        this.email = email;
        this.id = null;
        this.registerDate = null;
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

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getRegisterDate() {
        return this.registerDate;
    }

    public void setRegisterDate(Date registeredAt) {
        this.registerDate = registeredAt;
    }

    @Override
    public String toString() {
        return "Client: " + new JSONObject(this).toString();
    }
}
