package com.example.demo4.repository;

import com.example.demo4.model.Class;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassRepository extends JpaRepository <Class, Long> {
    @Query(value = "select * from class c where c.name =?1", nativeQuery = true)
    List<Class> findByName(String name);
}
