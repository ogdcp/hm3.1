package com.example.hm31;

import com.example.hm31.controller.FacultyController;
import com.example.hm31.model.Faculty;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

import static com.example.hm31.Constant.TEST_FACULTY_1;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FacultyControllerRestTemplateTest {
    @LocalServerPort
    private int port;

    @Autowired
    private FacultyController facultyController;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void contextLoads() throws Exception {
        Assertions.assertThat(facultyController).isNotNull();
    }

    @Test
    void facultyGet() throws Exception {
        Assertions.assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/faculty", Faculty[].class))
                .isNotNull();
    }

    @Test
    void facultyGetFromName() throws Exception {
        Assertions.assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/faculty?name=Воин", Faculty[].class))
                .isNotNull();
    }

    @Test
    void facultyGetFromColor() throws Exception {
        Assertions.assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/faculty?color=Жёлтый", Faculty[].class))
                .isNotNull();
    }

    @Test
    void facultyPost() throws Exception {
        Faculty faculty = new Faculty();
        faculty.setName("Name");
        faculty.setColor("Color");
        Assertions.assertThat(this.restTemplate.postForEntity("http://localhost:" + port + "/faculty", faculty, Faculty.class))
                .isNotNull();
    }

    @Test
    void facultyDelete() throws Exception {
        restTemplate.delete("http://localhost:" + port + "/faculty" + "/" + TEST_FACULTY_1.getId(), String.class);
    }
    @Test
    void facultyPut() throws Exception {
        this.restTemplate.put("http://localhost:" + port + "/faculty", TEST_FACULTY_1, String.class);
    }
}
