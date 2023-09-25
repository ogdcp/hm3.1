package com.example.hm31.repository;



import com.example.hm31.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findByAge(Integer age);

    List<Student> findByAgeBetween(Integer form, Integer to);

    List<Student> findByFacultyId(long id);
    @Query(value = "SELECT COUNT(*) FROM \"public\".student",nativeQuery = true)
    Long numberOfAllStudents();
    @Query(value = "SELECT AVG(age) FROM \"public\".student",nativeQuery = true)
    double averageAge();
    @Query(value = "SELECT * FROM \"public\".student ORDER BY \"public\".student.\"id\" DESC LIMIT 5",nativeQuery = true)
    List<Student> lastFiveStudent();
}