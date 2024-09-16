package com.project.Perseo_Academy.controllers;

import com.project.Perseo_Academy.models.Experience;
import com.project.Perseo_Academy.services.ExperienceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/experiences")
@CrossOrigin(origins = "*")
public class ExperienceController {

    @Autowired
    ExperienceService experienceService;

    @GetMapping
    @PreAuthorize("hasRole('USER')")
    public List<Experience> getAllExperience() {
        return experienceService.getAllExperience();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('USER')")
    public Optional<Experience> getExperienceById(@PathVariable Integer id) {
        return experienceService.getExperienceById(id);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('USER')")
    public Experience createExperience(@RequestBody Experience experience){
        return experienceService.createExperience(experience);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('USER')")
    public void updateExperience(@RequestBody Experience experience, @PathVariable Integer id) {
        experienceService.updateExperience(experience, id);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('USER')")
    public void deleteExperience(@PathVariable Integer id) {
        experienceService.deleteExperience(id);
    }
}
