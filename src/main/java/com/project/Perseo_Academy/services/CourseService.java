package com.project.Perseo_Academy.services;

import com.project.Perseo_Academy.models.Course;
import com.project.Perseo_Academy.repositories.ICourseRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    @Autowired
    ICourseRepository iCourseRepository;

    public List<Course> getAllCourse() {
        return iCourseRepository.findAll();
    }

    public Optional<Course> getCourseById(Integer id) {
        return iCourseRepository.findById(id);
    }

    public Course createCourse(Course Course) {
        return iCourseRepository.save(Course);
    }

    public Course updateCourse(Course course, Integer id) {
        if (iCourseRepository.existsById(id)) {
            course.setId(id);
            return iCourseRepository.save(course);
        } else {
            throw new EntityNotFoundException("Course not found with ID " + id);
        }
    }

    public void deleteCourse(Integer id) {
        iCourseRepository.deleteById(id);
    }
}


