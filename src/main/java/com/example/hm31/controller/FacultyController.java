package com.example.hm31.controller;

import com.example.hm31.model.Faculty;
import com.example.hm31.service.FacultyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;

@RestController
@RequestMapping("/faculty")
public class FacultyController {
    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @GetMapping("{id}")
    public ResponseEntity<Faculty> get(@PathVariable Long id) {
        Faculty faculty = facultyService.find(id);
        if (faculty == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(faculty);
    }
    @GetMapping("get")
    public HashMap<Long,Faculty> get () {
        return facultyService.getAll();
    }
    @PostMapping
    public Faculty add (@RequestBody Faculty faculty) {
        return facultyService.add(faculty);
    }
    @PutMapping
    public Faculty set (@RequestBody Faculty faculty) {
        return facultyService.set(faculty);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<Faculty> remove (@PathVariable Long id) {
        facultyService.remove(id);
        return ResponseEntity.ok().build();
    }
    @GetMapping
    public ResponseEntity <Collection <Faculty>> findFaculties(@RequestParam(required = false) String color) {
        if (color != null && !color.isBlank()) {
            return ResponseEntity.ok(facultyService.findByColor(color));
        }
        return ResponseEntity.ok(Collections.emptyList());
    }
}