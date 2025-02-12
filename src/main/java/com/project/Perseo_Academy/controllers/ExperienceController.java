package com.project.Perseo_Academy.controllers;


import com.project.Perseo_Academy.dto.request.ExperienceRequest;
import com.project.Perseo_Academy.models.Course;
import com.project.Perseo_Academy.models.Experience;
import com.project.Perseo_Academy.models.User;
import com.project.Perseo_Academy.repositories.IExperienceRepository;
import com.project.Perseo_Academy.repositories.IUserRepository;
import com.project.Perseo_Academy.services.ExperienceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/experiences")
@CrossOrigin(origins = "*")
public class ExperienceController {

    private final ExperienceService experienceService;
    private final IUserRepository iUserRepository;
    private final IExperienceRepository iExperienceRepository;

    public ExperienceController(ExperienceService experienceService, IUserRepository iUserRepository, IExperienceRepository iExperienceRepository) {
        this.experienceService = experienceService;
        this.iUserRepository = iUserRepository;
        this.iExperienceRepository = iExperienceRepository;
    }


    @GetMapping
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<List<Experience>> getExperiences() {
        List<Experience> experiences = experienceService.getAllExperience();
        return ResponseEntity.ok(experiences);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<Optional<Experience>> getExperienceById(@PathVariable Long id) {
        Optional<Experience> experience = experienceService.getExperienceById(id);
        return ResponseEntity.ok(experience);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<?> createExperience(@RequestBody ExperienceRequest request) {
        if (request.getUserId() == null) {
            throw new IllegalArgumentException("User ID must not be null");
        }

        User user = iUserRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Experience experience = new Experience();
        experience.setCompany(request.getCompany());
        experience.setPosition(request.getPosition());
        experience.setDescription(request.getDescription());
        experience.setStartDate(request.getStartDate());
        experience.setEndDate(request.getEndDate());
        experience.setUser(user);

        Experience savedExperience = iExperienceRepository.save(experience);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedExperience);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('USER')")
    public ResponseEntity<Experience> updateExperience(@RequestBody Experience experience, @PathVariable Long id) {
        Experience updatedExperience = experienceService.updateExperience(experience, id);
        return ResponseEntity.ok(updatedExperience);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<Void> deleteExperience(@PathVariable Long id) {
        experienceService.deleteExperience(id);
        return ResponseEntity.noContent().build();
    }
}

