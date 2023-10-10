package com.example.hm31.service;

import com.example.hm31.model.Faculty;
import com.example.hm31.repository.FacultyRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


import java.util.*;
import java.util.stream.Collectors;

@Service
public class FacultyService {
    private final FacultyRepository facultyRepository;
    private final Logger logger = LoggerFactory.getLogger(FacultyService.class);

    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public Faculty add(Faculty faculty) {
        logger.info("Был вызван метод add");
        return facultyRepository.save(faculty);
    }

    public Faculty find(long id) {
        logger.info("Был вызван метод find");
        return facultyRepository.findById(id).get();
    }

    public Faculty set(Faculty faculty) {
        logger.info("Был вызван метод set");
        return facultyRepository.save(faculty);
    }

    public void remove(long id) {
        logger.info("Был вызван метод remove");
        facultyRepository.deleteById(id);
    }

    public List<Faculty> getAll() {
        logger.info("Был вызван метод getAll");
        return facultyRepository.findAll();
    }

    public Collection<Faculty> findByColor(String color) {
        logger.info("Был вызван метод findByColor");
        return facultyRepository.findByColor(color);
    }

    public List<Faculty> findByNameOrColor(String name, String color) {
        logger.info("Был вызван метод findByNameOrColor");
        return facultyRepository.findByNameIgnoreCaseOrColorIgnoreCase(name, color);
    }
    public Faculty findStudent(long id)  {
        logger.info("Был вызван метод findStudent");
        return facultyRepository.findByStudentId(id);
    }
    public String longestName() {
        return facultyRepository.findAll().stream()
                .map(Faculty::getName)
                .sorted((x, y) -> y.length() - x.length())
                .collect(Collectors.toList())
                .get(0);
    }
}