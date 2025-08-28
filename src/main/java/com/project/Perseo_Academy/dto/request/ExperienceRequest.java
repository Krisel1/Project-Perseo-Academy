package com.project.Perseo_Academy.dto.request;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
public class ExperienceRequest {
    private String company;
    private String position;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private Long userId;
}
