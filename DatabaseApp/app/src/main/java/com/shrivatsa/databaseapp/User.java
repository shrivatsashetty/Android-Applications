package com.shrivatsa.databaseapp;

public class User {

    // attributes of an user
    private int id;
    private String name;
    private String password;

    // constructor overloading
    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    // constructor with 3 args
    public User(int id, String name, String password){
        this.id = id;
        this.name = name;
        this.password = password;
    }

    // creating getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
