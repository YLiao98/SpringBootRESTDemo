package com.example.yiwei.springbootrestdemo.controller;

import com.example.yiwei.springbootrestdemo.entity.Student;
import com.example.yiwei.springbootrestdemo.service.StudentService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @GetMapping("/students")
    public ResponseEntity<List<Student>> getAllStudents(){
        try {
            List<Student> students = studentService.findAllStudents();
            if (students.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(students, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    @GetMapping("/students/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable("id") long id){
        Student student = studentService.findById(id);
        if(student==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(student,HttpStatus.OK);
    }

    @PostMapping("/students")
    public ResponseEntity<Student> createStudent(@RequestBody Student student){
        try{
            Student newStudent = studentService.saveStudent(student);
            return new ResponseEntity<>(newStudent, HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/students/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable("id") Long id, @RequestBody Student student){
        Student _student = studentService.findById(id);
        if(_student != null){
            _student.setFirstname(student.getFirstname());
            _student.setLastname(student.getLastname());
            Student updatedStudent = studentService.updateStudent(_student);
            return new ResponseEntity<>(updatedStudent,HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/students/{id}")
    public ResponseEntity<HttpStatus> deleteStudentById(@PathVariable("id") Long id){
        try{
            studentService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/students")
    public ResponseEntity<HttpStatus> deleteStudents(){
        try{
            studentService.deleteStudents();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PatchMapping("/students/{id}")
    public ResponseEntity<Student> patchStudent(@PathVariable("id") Long id, @RequestBody Student student){
        try{
            Student updatedStudent = studentService.updateStudentFirstName(id, student.getFirstname());
            return new ResponseEntity<>(updatedStudent,HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
