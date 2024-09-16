package com.project.Perseo_Academy.repositories;

import com.project.Perseo_Academy.models.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICourseRepository extends JpaRepository<Course, Integer> {
}
