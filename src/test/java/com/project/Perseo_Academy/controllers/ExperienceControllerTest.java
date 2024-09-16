package com.project.Perseo_Academy.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.Perseo_Academy.models.Course;
import com.project.Perseo_Academy.models.Experience;
import com.project.Perseo_Academy.models.User;
import com.project.Perseo_Academy.services.ExperienceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ExperienceControllerTest {

    @Mock
    private ExperienceService experienceService;

    @InjectMocks
    private ExperienceController experienceController;

    private MockMvc mockMvc;
    private Experience experience;
    private Experience updateExperience;

    private List<Experience> experienceList = new ArrayList<>();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(experienceController).build();

        experience = new Experience();
        experience.setId(1);
        experience.setEnterprise("Amazon");
        experience.setPosition("Software Engineer");
        experience.setStartDate(LocalDate.of(2022, 3, 1));
        experience.setEndDate(LocalDate.of(2023, 11, 30));

        updateExperience = new Experience();
        updateExperience.setId(1);
        updateExperience.setEnterprise("Amazon");
        updateExperience.setPosition("Senior Software Engineer");
        updateExperience.setStartDate(LocalDate.of(2024, 1, 1));
        updateExperience.setEndDate(LocalDate.of(2024, 7, 30));

        experienceList.add(experience);
    }

    @Test
    void test_getAll_Experience() throws Exception {

        Experience exp1 = new Experience(1, "Amazon", "Software Engineer", LocalDate.of(2022, 3, 1), LocalDate.of(2023, 11, 30));
        Experience exp2 = new Experience(2, "Amazon", "Senior Software Engineer", LocalDate.of(2024, 1, 1), LocalDate.of(2024, 7, 30));

        List<Experience> experienceList = new ArrayList<>();
        experienceList.add(exp1);
        experienceList.add(exp2);

        when(experienceService.getAllExperience()).thenReturn(experienceList);

        mockMvc.perform(get("/api/experiences")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(
                        "[{\"id\":1,\"enterprise\":\"Amazon\",\"position\":\"Software Engineer\",\"startDate\":\"2022-03-01\",\"endDate\":\"2023-11-30\"}," +
                                "{\"id\":2,\"enterprise\":\"Amazon\",\"position\":\"Senior Software Engineer\",\"startDate\":\"2024-01-01\",\"endDate\":\"2024-07-30\"}]"));

        verify(experienceService).getAllExperience();
    }

    @Test
    void test_getExperience_By_Id() throws Exception {
        when(experienceService.getExperienceById(1)).thenReturn(Optional.of(experience));

        mockMvc.perform(get("/api/experiences/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"id\":1,\"enterprise\":\"Amazon\",\"position\":\"Software Engineer\",\"startDate\":\"2022-03-01\",\"endDate\":\"2023-11-30\"}"));

        verify(experienceService).getExperienceById(1);
    }

    @Test
    void test_create_Experience() throws Exception {
        when(experienceService.createExperience(any(Experience.class))).thenReturn(experience);

        String experienceJson =
                "{\"id\": 1,\n"
                        + "\"enterprise\": \"Amazon\",\n"
                        + "\"position\": \"Software Engineer\",\n"
                        + "\"startDate\": \"2022-03-01\",\n"
                        + "\"endDate\": 2023-11-30}";

        mockMvc
                .perform(post("/api/courses")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(experienceJson))
                .andExpect(status().isOk())
                .andExpect(content().json(
                        "{\"id\": 1,\n"
                                + "\"enterprise\": \"Amazon\",\n"
                                + "\"position\": \"Software Engineer\",\n"
                                + "\"startDate\": \"2022-03-01\",\n"
                                + "\"endDate\": 2023-11-30}"));
    }

    @Test
    void test_update_Experience() throws Exception {

        when(experienceService.updateExperience(any(Experience.class), any(Integer.class))).thenReturn(experience);

        String updateExperienceJson = "{\n"
                + "  \"id\": 1,\n"
                + "  \"enterprise\": \"Amazon\",\n"
                + "  \"position\": \"Senior Software Engineer\",\n"
                + "  \"startDate\": \"2024-01-01\",\n"
                + "  \"endDate\": \"2024-07-30\"\n"
                + "}";

        mockMvc
                .perform(put("/api/experiences/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updateExperienceJson))
                .andExpect(status().isOk())
                .andExpect(content().json(
                        "{\"id\": 1,\n"
                        + "\"enterprise\": \"Amazon\",\n"
                        + "\"Position\": \"Senior Software Engineer\",\n"
                        + "\"startDate\": \"2024-01-01\",\n"
                        + "\"endDate\": \"2024-07-30\"}"));

        verify(experienceService).updateExperience(any(Experience.class), eq(1));
    }

    @Test
    void test_delete_Experience() throws Exception {
        doNothing().when(experienceService).deleteExperience(any(Integer.class));

        mockMvc.perform(delete("/api/experiences/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(experienceService).deleteExperience(eq(1));
    }

}
