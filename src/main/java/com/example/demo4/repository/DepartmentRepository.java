package com.example.demo4.repository;

import com.example.demo4.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
    Optional<Department> findById(Long id);

    @Override
    boolean existsById(Long aLong);
}
