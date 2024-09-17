package com.project.Perseo_Academy.controllers;

import com.project.Perseo_Academy.models.Course;
import com.project.Perseo_Academy.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/courses")
@CrossOrigin(origins = "*")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping
    @PreAuthorize("permitALL()")
    public List<Course> getAllCourse() {
        return courseService.getAllCourse();
    }

    @GetMapping(path = "/{id}")
    @PreAuthorize("permitALL()")
    public Optional<Course> getCourseById(@PathVariable Integer id) {
        return courseService.getCourseById(id);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public Course createCourse(@RequestBody Course course){
        return courseService.createCourse(course);
    }

    @PutMapping(path = "/{id}")
    @PreAuthorize("hasAnyAuthority('MANAGER', 'ADMIN')")
    public Course updateCourse(@RequestBody Course course, @PathVariable Integer id) {
        return courseService.updateCourse(course, id);
    }

    @DeleteMapping(path = "/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void deleteCourse(@PathVariable Integer id) {
        courseService.deleteCourse(id);
    }
}
