package com.example.hm31.controller;

import com.example.hm31.model.Student;
import com.example.hm31.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.Collection;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("student")
public class StudentController {
    private  final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("{id}")
    public ResponseEntity<Student> get(@PathVariable Integer id) {
        Student student = studentService.find(id);
        if (student == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(student);
    }
    @PostMapping
    public Student add (@RequestBody Student student) {
        return studentService.add(student);
    }
    @PutMapping
    public Student set (@RequestBody Student student) {
        return studentService.set(student);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<Student> remove (@PathVariable Long id) {
        studentService.remove(id);
        return ResponseEntity.ok().build();
    }
    @GetMapping("get")
    public List<Student> get () {
        return studentService.getAll();
    }
    @GetMapping
    public ResponseEntity<Collection<Student>> findStudents(@RequestParam(required = false) int age) {
        if (age > 0) {
            return ResponseEntity.ok(studentService.findByAge(age));
        }
        return ResponseEntity.ok(Collections.emptyList());
    }
}