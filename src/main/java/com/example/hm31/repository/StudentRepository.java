package com.example.hm31.repository;

import com.example.hm31.model.Faculty;
import com.example.hm31.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface StudentRepository extends JpaRepository<Student,Integer> {
    void deleteAllById(long id);

    Collection<Student> findByAge(int age);
}
