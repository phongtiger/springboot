package com.example.demo4.service;

import com.example.demo4.model.Department;
import com.example.demo4.model.Student;

import java.util.List;

public interface DepartmentService {
    Department findById(Long id);
    List<Department> findAll();
    boolean existsById(Long id);
    void save(Department department);
    void delete(Department department);
}
