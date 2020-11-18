package com.example.demo4.model;

import java.util.List;

public class User {
    Long id;
    String name;
    String address;
    Long age;
    List<Long> idBook;

    public List<Long> getIdBook() {
        return idBook;
    }

    public void setIdBook(List<Long> idBook) {
        this.idBook = idBook;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getAge() {
        return age;
    }

    public void setAge(Long age) {
        this.age = age;
    }
}
