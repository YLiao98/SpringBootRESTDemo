package com.example.yiwei.springbootrestdemo.service;

import com.example.yiwei.springbootrestdemo.entity.Student;

import java.util.List;

public interface StudentService {
    List<Student> findAllStudents();
    Student findById(Long id);
    Student saveStudent(Student student);
    void deleteById(Long id);
    Student updateStudent(Student student);
    void deleteStudents();
    Student updateStudentFirstName(Long id, String firstname);
}
