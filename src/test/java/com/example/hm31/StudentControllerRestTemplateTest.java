package com.example.hm31;

import com.example.hm31.controller.StudentController;
import com.example.hm31.model.Student;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;


import java.util.stream.Collectors;

import static com.example.hm31.Constant.TEST_STUDENT_1;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StudentControllerRestTemplateTest {

    @LocalServerPort
    private int port;

    @Autowired
    private StudentController studentController;

    @Autowired
    private TestRestTemplate restTemplate;


    @Test
    void contextLoads() throws Exception {
        Assertions.assertThat(studentController).isNotNull();
    }

    @Test
    void studentGet() throws Exception {

        Assertions.assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/student", Student[].class))
                .isNotNull();

    }

    @Test
    void studentGetId() throws Exception {
        Assertions.assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/student?id=2", Student[].class))
                .isNotNull();

    }

    @Test
    void studentGetToFrom() throws Exception {
        Assertions.assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/student?to=2&from=36", Student[].class))
                .isNotNull();

    }

    @Test
    void studentPost() throws Exception {
        Student student = new Student();
        student.setName("Name");
        student.setAge(12);
        Assertions.assertThat(this.restTemplate.postForEntity("http://localhost:" + port + "/student", student, Student.class))
                .isNotNull();
    }
    private Long findStudent(Student student) {
        return studentController.getAll().stream()
                .filter(e -> e.getName().equals(student.getName()))
                .collect(Collectors.toList())
                .stream()
                .filter(e -> e.getAge() == student.getAge())
                .collect(Collectors.toList())
                .stream().findFirst()
                .get()
                .getId();
    }
    @Test
    void studentDelete() throws Exception {
        studentController.add(TEST_STUDENT_1);
        Long studentTestId = findStudent(TEST_STUDENT_1);
        restTemplate.delete("http://localhost:" + port + "/student" + "/" + studentTestId, String.class);
    }


    @Test
    void studentPut() throws Exception {
        this.restTemplate.put("http://localhost:" + port + "/student", TEST_STUDENT_1, Student.class);

    }


}
