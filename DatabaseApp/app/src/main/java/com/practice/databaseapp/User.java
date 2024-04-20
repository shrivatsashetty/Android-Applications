package com.practice.databaseapp;

public class User {
    // set some attributes for the user
    private int id;
    private String name;
    private String password;

    // parameterized constructor
    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    // constructor overloading
    public User(int id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }

    /* setter and getter methods */
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
