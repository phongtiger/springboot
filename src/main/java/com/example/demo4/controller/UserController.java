package com.example.demo4.controller;

import com.example.demo4.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/user")
public class UserController {
    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUser() {
        User user = new User();
        user.setId(1L);
        user.setName("a");
        List<User> list = new ArrayList<>();
        list.add(user);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    @GetMapping("/findAllUserAndBooks")
    public ResponseEntity<List<User>> findAllUserAndBooks() {
        User user = new User();
        user.setId(1L);
        user.setName("a");
        List<Long> idLst = new ArrayList<>();
        idLst.add(1L);
        user.setIdBook(idLst);
        List<User> list = new ArrayList<>();
        list.add(user);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
