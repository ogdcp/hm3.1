package com.example.hm31.service;

import com.example.hm31.model.Student;
import com.example.hm31.repository.StudentRepository;
import org.springframework.stereotype.Service;


import java.util.*;

@Service
public class StudentService {

   private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student add(Student student) {
        return studentRepository.save(student);
    }

    public Student find(Integer id) {
        return studentRepository.findById(id).get();
    }
    public Student set (Student student) {
        return  studentRepository.save(student);
    }
    public void remove (long id) {
        studentRepository.deleteAllById(id);
    }
    public List<Student> getAll () {
       return studentRepository.findAll();
    }


    public Collection<Student> findByAge(int age) {
        return studentRepository.findByAge(age);
    }

}