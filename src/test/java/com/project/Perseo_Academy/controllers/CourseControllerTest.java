package com.project.Perseo_Academy.controllers;

import com.project.Perseo_Academy.models.Course;
import com.project.Perseo_Academy.services.CourseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import java.util.ArrayList;
import java.util.Optional;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CourseControllerTest {

//    private MockMvc mockMvc;
//
//    @MockBean
//    private CourseService courseService;
//
//    @BeforeEach
//    void setup(WebApplicationContext webApplicationContext) {
//        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
//    }
//
//    @Test
//    void test_Get_All_Courses() throws Exception {
//        when(courseService.getAllCourses()).thenReturn(new ArrayList<>());
//        mockMvc.perform(get("/api/courses")
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk());
//
//        verify(courseService, times(1)).getAllCourses();
//    }
//
//    @Test
//    void test_Get_Course_By_Id() throws Exception {
//        Course course = new Course(1L, "Java Basics", "Learn Java from scratch", 30);
//
//        when(courseService.getCourseById(1L)).thenReturn(Optional.of(course));
//
//        mockMvc.perform(get("/api/courses/1")
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.id").value(1))
//                .andExpect(jsonPath("$.name").value("Java Basics"));
//
//        verify(courseService, times(1)).getCourseById(1L);
//    }
//
//    @Test
//    @WithMockUser(roles = "ADMIN")
//    void test_Create_Course() throws Exception {
//        Course course = new Course(1L, "Spring Boot", "Master Spring Boot", 40);
//
//        when(courseService.createCourse(any(Course.class))).thenReturn(course);
//
//        mockMvc.perform(post("/api/courses")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content("{\"id\":1,\"name\":\"Spring Boot\",\"description\":\"Master Spring Boot\",\"price\":40}"))
//                .andExpect(status().isCreated())
//                .andExpect(jsonPath("$.id").value(1))
//                .andExpect(jsonPath("$.name").value("Spring Boot"));
//
//        verify(courseService, times(1)).createCourse(any(Course.class));
//    }
//
//    @Test
//    void test_Update_Course() throws Exception {
//        Course updatedCourse = new Course(1L, "Advanced Java", "Deep dive into Java", 50);
//
//        when(courseService.updateCourse(any(Course.class), eq(1L))).thenReturn(updatedCourse);
//
//        mockMvc.perform(put("/api/courses/1")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content("{\"id\":1,\"name\":\"Advanced Java\",\"description\":\"Deep dive into Java\",\"price\":50}"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.id").value(1))
//                .andExpect(jsonPath("$.name").value("Advanced Java"));
//
//        verify(courseService, times(1)).updateCourse(any(Course.class), eq(1L));
//    }
//
//    @Test
//    void test_Delete_Course() throws Exception {
//        doNothing().when(courseService).deleteCourse(1L);
//
//        mockMvc.perform(delete("/api/courses/1"))
//                .andExpect(status().isNoContent());
//
//        verify(courseService, times(1)).deleteCourse(1L);
//    }
}


