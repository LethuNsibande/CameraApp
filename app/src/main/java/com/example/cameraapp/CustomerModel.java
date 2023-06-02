package com.example.cameraapp;
/*
Name: Nsibande Lethukuthula
Student Number: 2018227377
 */

public class CustomerModel {
    // Declaration of Variables
    public int id;
    public String name;
    public int age;
    public boolean isActive;

    // Constructors
    public CustomerModel(int id, String name, int age, boolean isActive) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.isActive = isActive;
    }

    public CustomerModel() {
    }

    // Output for Customer model
    @Override
    public String toString() {
        return name + " age=" + age + " active=" + isActive + " ID: " + id;
    }

    // Getters and Setters
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
