package com.example.demo4.service.impl;

import com.example.demo4.model.Department;
import com.example.demo4.model.Student;
import com.example.demo4.repository.DepartmentRepository;
import com.example.demo4.service.DepartmentService;
import com.fasterxml.jackson.databind.util.ArrayIterator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public Department findById(Long id) {
        return departmentRepository.findById(id).isPresent() ? departmentRepository.findById(id).get() : new Department();
    }

    @Override
    public List<Department> findAll() {
        return departmentRepository.findAll();
    }

    @Override
    public boolean existsById(Long id) {
        return departmentRepository.existsById(id);
    }

    @Override
    public void save(Department department) {
        departmentRepository.save(department);
    }

    @Override
    public void delete(Department department) {
        departmentRepository.delete(department);
    }

}
