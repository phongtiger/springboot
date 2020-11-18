package com.example.demo4.controller;

import com.example.demo4.model.Input;
import com.example.demo4.model.Message;
import com.example.demo4.model.Student;
import com.example.demo4.service.ClassService;
import com.example.demo4.service.StudentService;
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
@RequestMapping("/student")
public class StudentController {
    @Autowired
    Environment env;

    @Autowired
    private StudentService studentService;

    @Autowired
    private ClassService classService;

    @PostMapping("/create")
    public ResponseEntity<Message> createStudent(@RequestBody Student student) {
        Message message = new Message();
        try {
            assert student != null;
            Student student1 = studentService.findByStudent_code(student.getStudent_code());
            if (student1 == null) {
                Student student2 = new Student();
                student2.setName(student.getName());
                student2.setPassword(student.getPassword());
                student2.setStudent_code(student.getStudent_code());
                student2.setIs_monitor(student.isIs_monitor());
                studentService.save(student);
                message.setMessage("SUCCESS");
                message.setDescription(env.getProperty("create.student.success"));
                return new ResponseEntity<>(message, HttpStatus.OK);
            } else {
                message.setMessage("ERROR");
                message.setDescription(env.getProperty("create.student.not.success"));
                return new ResponseEntity<>(message,HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<Student>> getAllStudent() {
        try {
            List<Student> lstStudent = studentService.findAll();
            if (lstStudent.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(lstStudent, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/searchByName/", method = RequestMethod.GET)
    public ResponseEntity<List<Student>> getStudentByName(@RequestParam(value = "name") String name) {
        try {
            List<Student> student = studentService.findByName(name);
            if (student.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(student, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/searchByStudentCode/", method = RequestMethod.GET)
    public ResponseEntity<Student> getStudentByStudentCode(@RequestParam(value = "studentcode") String studentcode) {
        try {
            Student student = studentService.findByStudent_code(studentcode);
            if (student == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(student, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<Message> updateStudent(@RequestBody Student student) {
        Message message = new Message();
        try {
            if (student != null && student.getId() != null) {
                Student student1 = studentService.findById(student.getId());
                if (student1 == null) {
                    message.setMessage("ERROR");
                    message.setDescription(env.getProperty("not.found.student.id"));
                    return new ResponseEntity<>(message,HttpStatus.INTERNAL_SERVER_ERROR);
                }
                if (student.getName() != null) {
                    student1.setName(student.getName());
                    if (studentService.exitsByCode(student.getStudent_code())) {
                        student1.setStudent_code(student.getStudent_code());
                    }
                    student1.setIs_monitor(student.isIs_monitor());
                    if (classService.existsById(student.getClass_id())) {
                        student1.setClass_id(student.getClass_id());
                    }
                }
                studentService.save(student1);
                message.setMessage("SUCCESS");
                message.setDescription(env.getProperty("update.student.info.success"));
                return new ResponseEntity<>(message,HttpStatus.OK);
            } else {
                message.setMessage("ERROR");
                message.setDescription(env.getProperty("update.student.info.not.success"));
                return new ResponseEntity<>(message,HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/change/password")
    public ResponseEntity<Message> changePassword(@RequestBody Input input) {
        Message message = new Message();
        try {
            if (input == null || input.getStudent() == null) {
                message.setMessage("ERROR");
                message.setDescription(env.getProperty("update.student.info.not.success"));
                return new ResponseEntity<>(message,HttpStatus.INTERNAL_SERVER_ERROR);
            }
            if (input.getOldPassword() == null) {
                message.setMessage("ERROR");
                message.setDescription("Ban vui long nhap mat khau cu");
                return new ResponseEntity<>(message,HttpStatus.INTERNAL_SERVER_ERROR);
            }
            if (input.getNewPassword() == null) {
                message.setMessage("ERROR");
                message.setDescription("Ban vui long nhap mat khau moi");
                return new ResponseEntity<>(message,HttpStatus.INTERNAL_SERVER_ERROR);
            }
            Student studentInput = input.getStudent();
            if (studentInput.getId() != null) {
                Student studentRoot = studentService.findById(studentInput.getId());
                if (studentRoot == null) {
                    message.setMessage("ERROR");
                    message.setDescription(env.getProperty("not.found.student.id"));
                    return new ResponseEntity<>(message,HttpStatus.INTERNAL_SERVER_ERROR);
                }
                if (!input.getOldPassword().equals(studentRoot.getPassword())) {
                    message.setMessage("ERROR");
                    message.setDescription(env.getProperty("Ban da nhap sai mat khau, vui long nhap lai"));
                    return new ResponseEntity<>(message,HttpStatus.INTERNAL_SERVER_ERROR);
                }
                studentRoot.setPassword(input.getNewPassword());
                studentService.save(studentRoot);
                message.setMessage("SUCCESS");
                message.setDescription(env.getProperty("update.student.info.success"));
                return new ResponseEntity<>(message,HttpStatus.OK);
            } else {
                message.setMessage("ERROR");
                message.setDescription(env.getProperty("update.student.info.not.success"));
                return new ResponseEntity<>(message,HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Message> deleteStudent(@PathVariable Long id) {
        Message message = new Message();
        try {
            if (id != null) {
                Student student = studentService.findById(id);
                if (student == null) {
                    message.setMessage("ERROR");
                    message.setDescription(env.getProperty("not.found.student.id") + " " + id + "!");
                    return new ResponseEntity<>(message,HttpStatus.NOT_FOUND);
                }
                studentService.deleteStudent(student);
                message.setMessage("SUCCESS");
                message.setDescription(env.getProperty("delete.student"));
                return new ResponseEntity<>(message,HttpStatus.OK);
            } else {
                message.setMessage("ERROR");
                message.setDescription(env.getProperty("please.enter.id.student"));
                return new ResponseEntity<>(message,HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
