package com.example.demo4.service.impl;

import com.example.demo4.model.Class;
import com.example.demo4.repository.ClassRepository;
import com.example.demo4.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ClassServiceImpl implements ClassService {
    @Autowired
    private ClassRepository classRepository;
    @Override
    public List<com.example.demo4.model.Class> findAll() {
        return classRepository.findAll();
    }

    @Override
    public List<com.example.demo4.model.Class> findByName(String name) {
        return classRepository.findByName(name);
    }

    @Override
    public com.example.demo4.model.Class findById(Long id) {
        return classRepository.findById(id).isPresent()? classRepository.findById(id).get() : new com.example.demo4.model.Class();
    }

    @Override
    public void save(com.example.demo4.model.Class _class) {
        classRepository.save(_class);
    }

    @Override
    public boolean existsById(Long id) {
        return classRepository.existsById(id);
    }

    @Override
    public void delete(Class _class) {
        classRepository.delete(_class);
    }
}
