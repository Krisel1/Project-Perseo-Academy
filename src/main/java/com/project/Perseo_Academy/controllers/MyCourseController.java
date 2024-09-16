package com.project.Perseo_Academy.controllers;

import com.project.Perseo_Academy.models.MyCourse;
import com.project.Perseo_Academy.services.MyCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/my_courses")
@CrossOrigin(origins = "*")
public class MyCourseController {

    @Autowired
    MyCourseService myCourseService;

    @GetMapping
    @PreAuthorize("hasAuthority('USER')")
    public List<MyCourse> getAllMyCourse() {
        return myCourseService.getAllMyCourse();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('USER')")
    public Optional<MyCourse> getMyCourseById(@PathVariable Integer id) {
        return myCourseService.getMyCourseById(id);
    }

    @PostMapping
    public MyCourse createMyCourse(@RequestBody MyCourse myCourse) {
        return myCourseService.createMyCourse(myCourse);
    }
}
