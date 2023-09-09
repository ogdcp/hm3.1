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
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public ResponseEntity<List<Student>> get(@RequestParam(required = false) Long id,
                                             @RequestParam(required = false) Integer to,
                                             @RequestParam(required = false) Integer from) {
        if (id != null) {
            return ResponseEntity.ok(Collections.singletonList(studentService.find(id)));
        }
        if (from != null || to != null) {
            return ResponseEntity.ok(studentService.findByAgeBetween(from, to));
        }
        return ResponseEntity.ok(studentService.getAll());
    }

    @PostMapping
    public Student add(@RequestBody Student student) {
        return studentService.add(student);
    }

    @PutMapping
    public Student set(@RequestBody Student student) {
        return studentService.set(student);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Student> remove(@PathVariable Long id) {
        studentService.remove(id);
        return ResponseEntity.ok().build();
    }
    @GetMapping ("findFaculty")
    public ResponseEntity<Student> getStudent(@RequestParam Long id) {

        return ResponseEntity.ok(studentService.findFaculty(id));
    }

}