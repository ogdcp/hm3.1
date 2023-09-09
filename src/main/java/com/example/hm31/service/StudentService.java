package com.example.hm31.service;

import com.example.hm31.model.Faculty;
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

    public Student find(long id) {
        return studentRepository.findById(id).get();
    }

    public Student set(Student student) {
        return studentRepository.save(student);
    }

    public void remove(long id) {
        studentRepository.deleteById(id);
    }

    public List<Student> getAll() {
        return studentRepository.findAll();
    }

    public Collection<Student> findByAge(Integer age) {
        return studentRepository.findByAge(age);
    }

    public List<Student> findByAgeBetween(Integer from, Integer to) {
        return studentRepository.findByAgeBetween(from, to);
    }
//    public Student findFaculty (long id)  {
//        return studentRepository.findByIdOfFaculty(id);
//    }

}