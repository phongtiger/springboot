package com.example.demo4.service;

import java.util.List;

public interface ClassService {
    List<com.example.demo4.model.Class> findAll();
    List<com.example.demo4.model.Class> findByName(String name);
    com.example.demo4.model.Class findById(Long id);
    void save(com.example.demo4.model.Class _class);
    boolean existsById(Long id);
    void delete(com.example.demo4.model.Class _class);
}
