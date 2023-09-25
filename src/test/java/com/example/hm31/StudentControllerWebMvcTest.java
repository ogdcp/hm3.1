package com.example.hm31;

import com.example.hm31.controller.StudentController;
import com.example.hm31.model.Student;
import com.example.hm31.repository.AvatarRepository;
import com.example.hm31.repository.StudentRepository;
import com.example.hm31.service.AvatarService;
import com.example.hm31.service.StudentService;
import net.minidev.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static com.example.hm31.Constant.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(StudentController.class)
public class StudentControllerWebMvcTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentRepository studentRepository;
    @MockBean
    private AvatarRepository avatarRepository;

    @SpyBean
    private StudentService studentService;
    @SpyBean
    private AvatarService avatarService;

    @InjectMocks
    private StudentController studentController;
    @Test
    void saveStudentTest() throws Exception {
        when(studentRepository.save(any(Student.class))).thenReturn(TEST_STUDENT_1);
        JSONObject studentObject = new JSONObject();

        studentObject.put("id", TEST_STUDENT_1.getId());
        studentObject.put("name", TEST_STUDENT_1.getName());
        studentObject.put("age", TEST_STUDENT_1.getAge());

        mockMvc.perform(post("/student")
                        .content(studentObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(TEST_STUDENT_1.getId()))
                .andExpect(jsonPath("$.name").value(TEST_STUDENT_1.getName()))
                .andExpect(jsonPath("$.age").value(TEST_STUDENT_1.getAge()));
    }
    @Test
    void getIdStudentTest() throws Exception {
        when(studentRepository.findById(any(Long.class))).thenReturn(Optional.of(TEST_STUDENT_1));
        JSONObject studentObject = new JSONObject();

        studentObject.put("id", TEST_STUDENT_1.getId());
        studentObject.put("name", TEST_STUDENT_1.getName());
        studentObject.put("age", TEST_STUDENT_1.getAge());

        mockMvc.perform(get("/student?id=" + TEST_STUDENT_1.getId())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
    @Test
    void getStudentTest() throws Exception {
        when(studentRepository.findAll()).thenReturn(TEST_STUDENT_COLLECTION);
        mockMvc.perform(get("/student")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].id").value(TEST_STUDENT_1.getId()))
                .andExpect(jsonPath("$[0].name").value(TEST_STUDENT_1.getName()))
                .andExpect(jsonPath("$[0].age").value(TEST_STUDENT_1.getAge()))
                .andExpect(jsonPath("$[1].id").value(TEST_STUDENT_2.getId()))
                .andExpect(jsonPath("$[1].name").value(TEST_STUDENT_2.getName()))
                .andExpect(jsonPath("$[1].age").value(TEST_STUDENT_2.getAge()))
                .andExpect(jsonPath("$[2].id").value(TEST_STUDENT_3.getId()))
                .andExpect(jsonPath("$[2].name").value(TEST_STUDENT_3.getName()))
                .andExpect(jsonPath("$[2].age").value(TEST_STUDENT_3.getAge()))
                .andExpect(jsonPath("$[3].id").value(TEST_STUDENT_4.getId()))
                .andExpect(jsonPath("$[3].name").value(TEST_STUDENT_4.getName()))
                .andExpect(jsonPath("$[3].age").value(TEST_STUDENT_4.getAge()))
                .andExpect(jsonPath("$[4].id").value(TEST_STUDENT_5.getId()))
                .andExpect(jsonPath("$[4].name").value(TEST_STUDENT_5.getName()))
                .andExpect(jsonPath("$[4].age").value(TEST_STUDENT_5.getAge()));
    }
    @Test
    void getByAgeBetweenStudentTest() throws Exception {
        when(studentRepository.findByAgeBetween(any(Integer.class),any(Integer.class))).thenReturn(TEST_STUDENT_COLLECTION);
        mockMvc.perform(get("/student")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
    @Test
    void deleteStudentTest() throws Exception {
        mockMvc.perform(delete("/student" + "/" + TEST_STUDENT_1.getId())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
