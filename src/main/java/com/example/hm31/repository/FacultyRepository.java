package com.example.hm31.repository;

import com.example.hm31.model.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface FacultyRepository extends JpaRepository <Faculty,Integer> {
    void deleteAllById(long id);

    Collection<Faculty> findByColor(String color);
}
