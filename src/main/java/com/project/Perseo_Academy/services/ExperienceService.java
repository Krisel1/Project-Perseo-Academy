package com.project.Perseo_Academy.services;


import com.project.Perseo_Academy.models.Experience;
import com.project.Perseo_Academy.models.User;
import com.project.Perseo_Academy.repositories.IExperienceRepository;
import com.project.Perseo_Academy.repositories.IUserRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ExperienceService {

    private final IExperienceRepository iExperienceRepository;
    private final IUserRepository iUserRepository;

    public ExperienceService(IExperienceRepository iExperienceRepository, IUserRepository iUserRepository) {
        this.iExperienceRepository = iExperienceRepository;
        this.iUserRepository = iUserRepository;
    }

    public List<Experience> getAllExperience() {
        return iExperienceRepository.findAll();
    }

    public Optional<Experience> getExperienceById(Long id) {
        return iExperienceRepository.findById(id);
    }

    public Experience createExperience(Experience experience) {
        return iExperienceRepository.save(experience);
    }

    public Experience updateExperience(Experience experience, Long id) {
        if (id == null) {
            throw new IllegalArgumentException("The given id must not be null");
        }

        Experience existingExperience = iExperienceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Experience not found"));

        if (experience.getUser() == null) {
            experience.setUser(existingExperience.getUser());
        }


        if (experience.getUser() == null || experience.getUser().getId() == null) {
            throw new IllegalArgumentException("User ID must not be null");
        }

        User user = iUserRepository.findById(experience.getUser().getId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        existingExperience.setCompany(experience.getCompany());
        existingExperience.setPosition(experience.getPosition());
        existingExperience.setDescription(experience.getDescription());
        existingExperience.setStartDate(experience.getStartDate());
        existingExperience.setEndDate(experience.getEndDate());
        existingExperience.setUser(user);

        return iExperienceRepository.save(existingExperience);
    }

    public void deleteExperience(Long id) {
        iExperienceRepository.deleteById(id);
    }
}



