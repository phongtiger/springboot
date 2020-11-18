package com.example.demo4.controller;

import com.example.demo4.model.Department;
import com.example.demo4.model.Message;
import com.example.demo4.model.Student;
import com.example.demo4.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@PropertySource(
        {"classpath:message.properties",
                "classpath:application.properties"}
)
@RequestMapping("/department")
public class DepartmentController {
    @Autowired
    Environment env;

    @Autowired
    private DepartmentService departmentService;

    @PostMapping("/create")
    public ResponseEntity<Message> createStudent(@RequestBody Department department) {
        Message message = new Message();
        System.out.println("hello");
        try {
            if (department != null) {
                departmentService.save(department);
                message.setMessage("SUCCESS");
                message.setDescription(env.getProperty("create.department.success"));
                return new ResponseEntity<>(message, HttpStatus.OK);
            } else {
                message.setMessage("ERROR");
                message.setDescription(env.getProperty("create.department.not.success"));
                return new ResponseEntity<>(message,HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<Department>> getAllStudent() {
        try {
            List<Department> lstDepartment = departmentService.findAll();
            if (lstDepartment.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(lstDepartment, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
