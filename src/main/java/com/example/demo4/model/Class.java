package com.example.demo4.model;

import javax.persistence.*;

import org.springframework.stereotype.Component;

@Entity
@Table(name = "class")
public class Class {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Long department_id;

    private String lead_student_code;

    public Class() {
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

    public Long getDepartment_id() {
        return department_id;
    }

    public void setDepartment_id(Long department_id) {
        this.department_id = department_id;
    }

    public String getLead_student_code() {
        return lead_student_code;
    }

    public void setLead_student_code(String lead_student_code) {
        this.lead_student_code = lead_student_code;
    }
}
