package com.project.Perseo_Academy.services;

import com.project.Perseo_Academy.models.MyCourse;
import com.project.Perseo_Academy.repositories.IMyCourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MyCourseService {

    @Autowired
    IMyCourseRepository iMyCourseRepository;

    public List<MyCourse> getAllMyCourse() {
        return iMyCourseRepository.findAll();
    }

    public Optional<MyCourse> getMyCourseById(Integer id) {
        return iMyCourseRepository.findById(id);
    }

    public MyCourse createMyCourse(MyCourse myCourse) {
        return iMyCourseRepository.save(myCourse);
    }

}
