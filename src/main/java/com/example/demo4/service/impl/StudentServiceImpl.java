package com.example.demo4.service.impl;

import com.example.demo4.model.Student;
import com.example.demo4.repository.StudentRepository;
import com.example.demo4.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    StudentRepository studentRepository;

    @Override
    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    @Override
    public Student findById(Long id) {
        return studentRepository.findByStudentId(id);
    }

    @Override
    public boolean existsById(Long id) {
        return studentRepository.existsById(id);
    }

    @Override
    public boolean exitsByCode(String code) {
        Student student = studentRepository.findByStudent_code(code);
        return student != null;
    }

    @Override
    public List<Student> findByName(String name) {
        return studentRepository.findByName(name);
    }

    @Override
    public Student findByStudent_code(String student_code) {
        return studentRepository.findByStudent_code(student_code);
    }

    @Override
    public List<Student> findAllByClassId(Long id) {
        return studentRepository.findAllStudentByClassId(id);
    }

    @Override
    public void save(Student student) {
        studentRepository.save(student);
    }

    @Override
    public void deleteStudent(Student id) {
        studentRepository.delete(id);
    }
}
