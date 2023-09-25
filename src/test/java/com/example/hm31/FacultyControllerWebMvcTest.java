package com.example.hm31;

import com.example.hm31.controller.FacultyController;
import com.example.hm31.model.Faculty;
import com.example.hm31.repository.FacultyRepository;
import com.example.hm31.service.FacultyService;
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


@WebMvcTest(FacultyController.class)
public class FacultyControllerWebMvcTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FacultyRepository facultyRepository;

    @SpyBean
    private FacultyService facultyService;

    @InjectMocks
    private FacultyController facultyController;
    @Test
    void saveFacultyTest() throws Exception {
        when(facultyRepository.save(any(Faculty.class))).thenReturn(TEST_FACULTY_1);
        JSONObject FacultyObject = new JSONObject();

        FacultyObject.put("id", TEST_FACULTY_1.getId());
        FacultyObject.put("name", TEST_FACULTY_1.getName());
        FacultyObject.put("color", TEST_FACULTY_1.getColor()
        );

        mockMvc.perform(post("/faculty")
                        .content(FacultyObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(TEST_FACULTY_1.getId()))
                .andExpect(jsonPath("$.name").value(TEST_FACULTY_1.getName()))
                .andExpect(jsonPath("$.color").value(TEST_FACULTY_1.getColor()
                ));
    }
    @Test
    void getIdFacultyTest() throws Exception {
        when(facultyRepository.findById(any(Long.class))).thenReturn(Optional.of(TEST_FACULTY_1));
        JSONObject FacultyObject = new JSONObject();

        FacultyObject.put("id", TEST_FACULTY_1.getId());
        FacultyObject.put("name", TEST_FACULTY_1.getName());
        FacultyObject.put("color", TEST_FACULTY_1.getColor());

        mockMvc.perform(get("/faculty?id=" + TEST_FACULTY_1.getId())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
    @Test
    void getFacultyTest() throws Exception {
        when(facultyRepository.findAll()).thenReturn(TEST_FACULTY_COLLECTION);
        mockMvc.perform(get("/faculty")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].id").value(TEST_FACULTY_1.getId()))
                .andExpect(jsonPath("$[0].name").value(TEST_FACULTY_1.getName()))
                .andExpect(jsonPath("$[0].color").value(TEST_FACULTY_1.getColor()
                ))
                .andExpect(jsonPath("$[1].id").value(TEST_FACULTY_2.getId()))
                .andExpect(jsonPath("$[1].name").value(TEST_FACULTY_2.getName()))
                .andExpect(jsonPath("$[1].color").value(TEST_FACULTY_2.getColor()
                ))
                .andExpect(jsonPath("$[2].id").value(TEST_FACULTY_3.getId()))
                .andExpect(jsonPath("$[2].name").value(TEST_FACULTY_3.getName()))
                .andExpect(jsonPath("$[2].color").value(TEST_FACULTY_3.getColor()
                ))
                .andExpect(jsonPath("$[3].id").value(TEST_FACULTY_4.getId()))
                .andExpect(jsonPath("$[3].name").value(TEST_FACULTY_4.getName()))
                .andExpect(jsonPath("$[3].color").value(TEST_FACULTY_4.getColor()));
    }
    @Test
    void findByNameOrColorFacultyTest() throws Exception {
        JSONObject facultyObject = new JSONObject();

        facultyObject.put("id", TEST_FACULTY_1.getId());
        facultyObject.put("name", TEST_FACULTY_1.getName());
        facultyObject.put("color", TEST_FACULTY_1.getColor());

        mockMvc.perform(get("/faculty?Color=" + TEST_FACULTY_1.getColor())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
    @Test
    void deleteFacultyTest() throws Exception {
        mockMvc.perform(delete("/faculty"+ "/" + TEST_FACULTY_1.getId())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
