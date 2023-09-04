package com.example.hm31.service;

import com.example.hm31.model.Faculty;
import com.example.hm31.repository.FacultyRepository;
import org.springframework.stereotype.Service;


import java.util.*;

@Service
public class FacultyService {
    private final FacultyRepository facultyRepository;

    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public Faculty add(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    public Faculty find(long id) {
        return facultyRepository.findById(id).get();
    }

    public Faculty set(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    public void remove(long id) {
        facultyRepository.deleteById(id);
    }

    public List<Faculty> getAll() {
        return facultyRepository.findAll();
    }

    public Collection<Faculty> findByColor(String color) {
        return facultyRepository.findByColor(color);
    }

    public List<Faculty> findByNameOrColor(String name, String color) {
        return facultyRepository.findByNameIgnoreCaseOrColorIgnoreCase(name, color);
    }
}