package com.project.Perseo_Academy.services;

import com.project.Perseo_Academy.models.Experience;
import com.project.Perseo_Academy.repositories.IExperienceRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExperienceService {

    @Autowired
    IExperienceRepository iExperienceRepository;

    public List<Experience> getAllExperience() {
        return iExperienceRepository.findAll();
    }

    public Optional<Experience> getExperienceById(Integer id) {
        return iExperienceRepository.findById(id);
    }

    public Experience createExperience(Experience experience) {
        return iExperienceRepository.save(experience);
    }

    public Experience updateExperience(Experience experience, Integer id) {
        if (iExperienceRepository.existsById(id)) {
            experience.setId(id);
            return iExperienceRepository.save(experience);
        } else {
            throw new EntityNotFoundException("Experience not found with ID " + id);
        }
    }

    public void deleteExperience(Integer id) {
        iExperienceRepository.deleteById(id);
    }
}
