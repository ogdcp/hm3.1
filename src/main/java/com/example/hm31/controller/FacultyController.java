package com.example.hm31.controller;

import com.example.hm31.model.Faculty;
import com.example.hm31.service.FacultyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.Collection;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/faculty")
public class FacultyController {
    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @GetMapping
    public ResponseEntity<List<Faculty>> get(@RequestParam(required = false) Long id,
                                             @RequestParam(required = false) String name,
                                             @RequestParam(required = false) String color) {
        if (id != null) {
            return ResponseEntity.ok(Collections.singletonList(facultyService.find(id)));
        }
        if ((name != null && !name.isBlank()) || (color != null && !color.isBlank())) {
            return ResponseEntity.ok(facultyService.findByNameOrColor(name, color));
        }
        return ResponseEntity.ok(facultyService.getAll());
    }


    @PostMapping
    public Faculty add(@RequestBody Faculty faculty) {
        return facultyService.add(faculty);
    }

    @PutMapping
    public Faculty set(@RequestBody Faculty faculty) {
        return facultyService.set(faculty);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Faculty> remove(@PathVariable Long id) {
        facultyService.remove(id);
        return ResponseEntity.ok().build();
    }

}