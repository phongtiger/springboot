package com.example.demo4.service;

import com.example.demo4.model.Student;

import java.util.List;

public interface StudentService {
    List<Student> findAll();
    Student findById(Long id);
    boolean existsById(Long id);
    boolean exitsByCode(String code);
    List<Student> findByName(String name);
    Student findByStudent_code(String student_code);
    List<Student> findAllByClassId(Long id);
    void save(Student student);
    void deleteStudent(Student id);
}
