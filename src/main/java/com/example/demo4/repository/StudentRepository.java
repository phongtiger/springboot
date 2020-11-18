package com.example.demo4.repository;

import com.example.demo4.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findAll();
    @Query(value = "select * from student s where  s.name like ?1", nativeQuery = true)
    List<Student> findByName(String name);
    @Query(value = "select * from student s where s.student_code = ?1", nativeQuery = true)
    Student findByStudent_code(String student_code);
    @Query(value = "select * from student s where s.id = ?1", nativeQuery = true)
    Student findByStudentId(Long id);
    @Query(value = "select * from student s where s.class_id = ?1", nativeQuery = true)
    List<Student> findAllStudentByClassId(Long id);
}
