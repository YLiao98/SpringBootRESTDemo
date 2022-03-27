package com.example.yiwei.springbootrestdemo.service;

import com.example.yiwei.springbootrestdemo.dao.StudentRepository;
import com.example.yiwei.springbootrestdemo.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService{

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public List<Student> findAllStudents() {
        List<Student> studentList = new ArrayList<>();
        studentRepository.findAll().forEach(studentList::add);
        return studentList;
    }

    @Override
    public Student findById(Long id) {
        return studentRepository.findById(id).orElse(null);
    }

    @Override
    public Student saveStudent(Student student) {
        return studentRepository.save(new Student(student.getId(),student.getFirstname(),student.getLastname()));
    }

    @Override
    public void deleteById(Long id) {
        studentRepository.deleteById(id);
    }
    @Override
    public void deleteStudents(){
        studentRepository.deleteAll();
    }

    @Override
    public Student updateStudentFirstName(Long id, String firstname) {
        Optional<Student> _student = studentRepository.findById(id);
        if(_student.isPresent()){
            Student newStudent = _student.get();
            newStudent.setFirstname(firstname);
            return studentRepository.save(newStudent);
        }else{
            throw new NullPointerException();
        }
    }

    @Override
    public Student updateStudent(Student student) {
        Optional<Student> _student = studentRepository.findById(student.getId());
        if(_student.isPresent()){
            return studentRepository.save(_student.get());
        }else{
            throw new NullPointerException();
        }
    }


}
