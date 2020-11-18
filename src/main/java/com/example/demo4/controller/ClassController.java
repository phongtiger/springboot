package com.example.demo4.controller;

import com.example.demo4.model.Class;
import com.example.demo4.model.Message;
import com.example.demo4.model.Student;
import com.example.demo4.service.ClassService;
import com.example.demo4.service.DepartmentService;
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
@RequestMapping("/class")
public class ClassController {
    @Autowired
    Environment env;

    @Autowired
    private ClassService classService;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private StudentService studentService;


    @PostMapping("/create")
    public ResponseEntity<Message> createClass(@RequestBody Class _class) {
        Message message = new Message();
        try {
            if (_class != null) {
                return getMessageResponseEntityUpdateClass(_class, message);
            } else {
                message.setMessage("ERROR");
                message.setDescription(env.getProperty("create.class.not.success"));
                return new ResponseEntity<>(message,HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<Class>> getAllClass() {
        try {
            List<Class> lstClass = classService.findAll();
            if (lstClass.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(lstClass, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/findByName")
    public ResponseEntity<List<Class>> findByName(@RequestParam(name = "name") String name) {
        try {
            List<Class> _class = classService.findByName(name);
            if (_class == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(_class, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/mentor")
    public ResponseEntity<Student> findMentor(@RequestParam(name = "idClass") Long idClass) {
        try {
            Class _class = classService.findById(idClass);
            if (_class == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                String mentorCode = _class.getLead_student_code();
                Student student = studentService.findByStudent_code(mentorCode);
                if (student == null) {
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                } else {
                    return new ResponseEntity<>(student, HttpStatus.OK);
                }
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/allStudentInAClass")
    public ResponseEntity<List<Student>> getAllStudentInAClass(@RequestParam(name = "idClass") Long idClass) {
        try {
            Class _class = classService.findById(idClass);
            if (_class == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                Long classId = _class.getId();
                List<Student> lstStudent = studentService.findAllByClassId(classId);
                if (lstStudent.isEmpty()) {
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                } else {
                    return new ResponseEntity<>(lstStudent, HttpStatus.OK);
                }
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<Message> updateClass(@RequestBody Class _class) {
        Message message = new Message();
        try {
            if (_class != null) {
                if ((_class.getId() != null) && !classService.existsById(_class.getId())) {
                    message.setMessage("ERROR");
                    message.setDescription(env.getProperty("not.exit.id.class"));
                    return new ResponseEntity<>(message,HttpStatus.INTERNAL_SERVER_ERROR);
                }
                return getMessageResponseEntityUpdateClass(_class, message);
            } else {
                message.setMessage("ERROR");
                message.setDescription(env.getProperty("create.class.not.success"));
                return new ResponseEntity<>(message,HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private ResponseEntity<Message> getMessageResponseEntityUpdateClass(@RequestBody Class _class, Message message) {
        if ((_class.getDepartment_id() != null) && !departmentService.existsById(_class.getDepartment_id())) {
            message.setMessage("ERROR");
            message.setDescription(env.getProperty("not.exit.id.department"));
            return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        Class _class2 = new Class();
        _class2.setName(_class.getName());
        _class2.setDepartment_id(_class.getDepartment_id());
        _class2.setLead_student_code(_class.getLead_student_code());
        classService.save(_class);
        message.setMessage("SUCCESS");
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

}
