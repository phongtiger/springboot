package com.example.demo4.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sun.applet.Main;


@CrossOrigin("*")
@RestController
public class QuizzController {
    static String response = "";
    @GetMapping("/version")
    public ResponseEntity<String> getAllUser() {
        response = Main.theVersion;
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping("/hello/{name}")
    public ResponseEntity<String> hello(@PathVariable("name") String name) {
        String response = "Hello " + name;
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @PostMapping("/quizz/{string}")
    public ResponseEntity<String> create(@PathVariable("string") String string) {
        response = string;
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @PutMapping("/quizz/{string}")
    public ResponseEntity<String> put(@PathVariable("string") String string) {
        response = string;
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @DeleteMapping("/quizz")
    public ResponseEntity<String> delete(@PathVariable("string") String string) {
        response = string;
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
