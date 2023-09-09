package com.example.hm31.repository;



import com.example.hm31.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findByAge(Integer age);

    List<Student> findByAgeBetween(Integer form, Integer to);

//    Student findByIdOfFaculty(long id);
}