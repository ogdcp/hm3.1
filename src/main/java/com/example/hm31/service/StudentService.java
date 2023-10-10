package com.example.hm31.service;

import com.example.hm31.model.Faculty;
import com.example.hm31.model.Student;
import com.example.hm31.repository.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    private final Logger logger = LoggerFactory.getLogger(StudentService.class);
    private final Object object = new Object();

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student add(Student student) {
        logger.info("Был вызван метод add");
        return studentRepository.save(student);
    }

    public Student find(long id) {
        logger.info("Был вызван метод find");
        return studentRepository.findById(id).get();
    }

    public Student set(Student student) {
        logger.info("Был вызван метод set");
        return studentRepository.save(student);
    }

    public void remove(long id) {
        logger.info("Был вызван метод remove");
        studentRepository.deleteById(id);
    }

    public List<Student> getAll() {
        logger.info("Был вызван метод getAll");
        return studentRepository.findAll();
    }

    public Collection<Student> findByAge(Integer age) {
        logger.info("Был вызван метод findByAge");
        return studentRepository.findByAge(age);
    }

    public List<Student> findByAgeBetween(Integer from, Integer to) {
        logger.info("Был вызван метод findByAgeBetween");
        return studentRepository.findByAgeBetween(from, to);
    }
    public Student findFaculty (long id)  {
        logger.info("Был вызван метод findFaculty");
        return (Student) studentRepository.findByFacultyId(id);
    }
    public Long numberOfAllStudents() {
        logger.info("Был вызван метод numberOfAllStudents");
        return studentRepository.numberOfAllStudents();
    }
    public double averageAge () {
        logger.info("Был вызван метод averageAge");
        return studentRepository.averageAge();
    }

    public List<Student> lastFiveStudent() {
        logger.info("Был вызван метод lastFiveStudent");
        return studentRepository.lastFiveStudent();
    }
    public List<String> findAllStartFromA() {
        return studentRepository.findAll().stream()
                .map(Student::getName)
                .map(String::toUpperCase)
                .filter(name -> name.startsWith("А"))
                .sorted()
                .collect(Collectors.toList());
    }

    public double avgAge() {
        return studentRepository.findAll().stream()
                .mapToDouble(i -> (double) i.getAge())
                .average()
                .orElseThrow(() -> new RuntimeException("Ошибка вычисления среднего возраста"));
    }

    public int calculate() {
        long start = System.currentTimeMillis();
        int result = Stream
                .iterate(1, a -> a + 1)
                .limit(1_000_000)
                .parallel()
                .reduce(0, (a, b) -> a + b);
        long finish = System.currentTimeMillis();
        logger.info("Calculate time: " + (finish - start));
        return result;
    }
    public void printStudentsName() {
        List<Student> students = studentRepository.findAll();
        printStudentName(students.get(0));
        printStudentName(students.get(1));

        new Thread(() -> {
            printStudentName(students.get(2));
            printStudentName(students.get(3));
        }).start();

        new Thread(() -> {
            printStudentName(students.get(4));
            printStudentName(students.get(5));
        }).start();
    }

    private void printStudentName(Student student) {
        System.out.println(Thread.currentThread() + " " + student);
    }

    public void printStudentsNameSync() {
        List<Student> students = studentRepository.findAll();
        printStudentName(students.get(0));
        printStudentName(students.get(1));

        new Thread(() -> {
            printStudentName(students.get(2));
            printStudentName(students.get(3));
        }).start();

        new Thread(() -> {
            printStudentName(students.get(4));
            printStudentName(students.get(5));
        }).start();
    }

    private void printStudentNameSync(Student student) {
        synchronized (object) {
            System.out.println(Thread.currentThread() + " " + student);
        }
    }


}