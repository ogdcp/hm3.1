package com.example.hm31.service;

import com.example.hm31.model.Student;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Objects;

@Service
public class StudentService {
    private final HashMap<Long, Student> staffStudent = new HashMap<>();
    private long idLast = 0;

    public Student add(Student student) {
        student.setId(++idLast);
        staffStudent.put(student.getId(), student);
        return student;
    }

    public Student find(long id) {
        return staffStudent.get(id);
    }
    public Student set (Student student) {
        if (!staffStudent.containsKey(student.getId())) {
            return null;
        }
        staffStudent.put(student.getId(), student);
        return student;
    }
    public Student remove (long id) {
        return staffStudent.remove(id);
    }
    public HashMap<Long, Student> getAll () {
        return staffStudent;
    }

    public HashMap<Long, Student> filterAge(int age) {
        HashMap<Long,Student> ageFilter = new HashMap<>();
        for (int i = 0; i < staffStudent.size(); i++) {
            if (staffStudent.get(i).getAge() == age) {
                ageFilter.put(staffStudent.get(i).getId(),staffStudent.get(i));
            }
        }
        return ageFilter;
    }
    public Collection<Student> findByAge(int age) {
        ArrayList <Student> result = new ArrayList<>();
        for (Student student : staffStudent.values()) {
            if (student.getAge() == age) {
                result.add(student);
            }
        }
        return result;
    }
}