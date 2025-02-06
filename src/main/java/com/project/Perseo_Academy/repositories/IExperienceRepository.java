package com.project.Perseo_Academy.repositories;

import com.project.Perseo_Academy.models.Experience;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IExperienceRepository extends JpaRepository<Experience, Long> {
    Optional<Experience> findById(Long Id);
}
