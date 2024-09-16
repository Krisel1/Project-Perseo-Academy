package com.project.Perseo_Academy.services;

import com.project.Perseo_Academy.models.Course;
import com.project.Perseo_Academy.repositories.ICourseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CourseServiceTest {

    @Mock
    private ICourseRepository iCourseRepository;

    @InjectMocks
    private CourseService courseService;

    private Course course;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        course = new Course();
        course.setId(1);
        course.setName("Java Basics");
        course.setDescription("Introduction to Java");
        course.setPrice(99.99);

    }

    @Test
    void test_Create_Course() {
        when(iCourseRepository.save(any(Course.class))).thenReturn(course);

        Course result = courseService.createCourse(course);

        assertNotNull(result);
        assertEquals(1, result.getId());
        assertEquals("Java Basics", result.getName());
        assertEquals("Introduction to Java", result.getDescription());
        assertEquals(99.99, result.getPrice());

        verify(iCourseRepository, times(1)).save(any(Course.class));
    }

    @Test
    void test_update_Course() {
        when(iCourseRepository.save(any(Course.class))).thenReturn(course);

        Course result = courseService.updateCourse(course, 2);

        assertNotNull(result);
        assertEquals(1, result.getId());
        assertEquals("Java Basics", result.getName());
        assertEquals("Introduction to Java", result.getDescription());
        assertEquals(99.99, result.getPrice());

        verify(iCourseRepository, times(1)).save(any(Course.class));
    }
}