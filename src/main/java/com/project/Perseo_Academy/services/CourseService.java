package com.project.Perseo_Academy.services;

import com.project.Perseo_Academy.models.Course;
import com.project.Perseo_Academy.repositories.ICourseRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    private final ICourseRepository iCourseRepository;

    public CourseService(ICourseRepository iCourseRepository) {
        this.iCourseRepository = iCourseRepository;
    }

    public List<Course> getAllCourses() {
        return iCourseRepository.findAll();
    }

    public Optional<Course> getCourseById(Long id) {
        return iCourseRepository.findById(id);
    }

    public Course createCourse(Course Course) {
        return iCourseRepository.save(Course);
    }

    public Course updateCourse(Course updatedCourse, Long id) {
        Course existingCourse = iCourseRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Course not found with ID " + id));

        existingCourse.setName(updatedCourse.getName());
        existingCourse.setDescription(updatedCourse.getDescription());
        existingCourse.setPrice(updatedCourse.getPrice());

        return iCourseRepository.save(existingCourse);
    }

    public void deleteCourse(Long id) {
        iCourseRepository.deleteById(id);
    }
}


