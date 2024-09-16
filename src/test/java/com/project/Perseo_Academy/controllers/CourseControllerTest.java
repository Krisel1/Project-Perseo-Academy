package com.project.Perseo_Academy.controllers;

import com.project.Perseo_Academy.models.Course;
import com.project.Perseo_Academy.services.CourseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class CourseControllerTest {

    @Mock
    private CourseService courseService;

    @InjectMocks
    private CourseController courseController;

    private MockMvc mockMvc;
    private Course course;
    private Course updateCourse;

    private List<Course> courseList = new ArrayList<>();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(courseController).build();

        course = new Course();
        course.setId(1);
        course.setName("Java Basics");
        course.setDescription("Introduction to Java");
        course.setPrice(99.99);

        updateCourse = new Course();
        updateCourse.setId(1);
        updateCourse.setName("Java Advanced");
        updateCourse.setDescription("Advanced Java Concepts");
        updateCourse.setPrice(129.99);

        courseList.add(course);
    }

    @Test
    void test_getAll_Course() throws Exception {
        List<Course> courseList = new ArrayList<>();
        courseList.add(new Course(1, "Java Basics", "Introduction to Java", 99.99));
        courseList.add(new Course(2, "Spring Boot", "Learn Spring Boot", 149.99));

        when(courseService.getAllCourse()).thenReturn(courseList);

        mockMvc.perform(get("/api/courses")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(
                "[{id:1,name:\"Java Basics\",description:\"Introduction to Java\",price:99.99}," +
                "{id:2,name:\"Spring Boot\",description:\"Learn Spring Boot\",price:149.99}]"));

        verify(courseService, times(1)).getAllCourse();
    }

    @Test
    void test_getCourse_By_Id() throws Exception {
        Course course = new Course(1, "Java Basics", "Introduction to Java", 99.99);

        when(courseService.getCourseById(1)).thenReturn(Optional.of(course));

        mockMvc.perform(get("/api/courses/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{id:1,name:\"Java Basics\",description:\"Introduction to Java\",price:99.99}"));  // Verificamos que el JSON es el esperado

        verify(courseService).getCourseById(1);
    }

    @Test
    void test_create_Course() throws Exception {
        when(courseService.createCourse(any(Course.class))).thenReturn(course);

        String courseJson =
                "{\"id\": 1,\n"
                + "\"name\": \"Java Basics\",\n"
                + "\"description\": \"Introduction to Java\",\n"
                + "\"price\": 99.99}";

        mockMvc
                .perform(post("/api/courses")
                .contentType(MediaType.APPLICATION_JSON)
                .content(courseJson))
                .andExpect(status().isOk())
                .andExpect(content().json(
                        "{\"id\": 1,\n"
                         + "\"name\": \"Java Basics\",\n"
                         + "\"description\": \"Introduction to Java\",\n"
                         + "\"price\": 99.99}"));
    }

    @Test
    void test_update_Course() throws Exception {
        when(courseService.updateCourse(any(Course.class), any(Integer.class))).thenReturn(updateCourse);

        String updateCourseJson =
                "{\"id\": 1,\n"
                + "\"name\": \"Java Advanced\",\n"
                + "\"description\": \"Advanced Java Concepts\",\n"
                + "\"price\": 129.99}";

        mockMvc
                .perform(put("/api/courses/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updateCourseJson))
                .andExpect(status().isOk())
                .andExpect(content().json(
                        "{\"id\": 1,\n"
                        + "\"name\": \"Java Advanced\",\n"
                        + "\"description\": \"Advanced Java Concepts\",\n"
                        + "\"price\": 129.99}"));

        verify(courseService).updateCourse(any(Course.class), eq(1));
    }

    @Test
    void test_Delete_Course() throws Exception {
        doNothing().when(courseService).deleteCourse(any(Integer.class));

        mockMvc.perform(delete("/api/courses/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(courseService).deleteCourse(eq(1));
    }
}

