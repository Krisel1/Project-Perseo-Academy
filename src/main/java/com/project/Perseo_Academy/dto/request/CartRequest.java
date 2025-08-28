package com.project.Perseo_Academy.dto.request;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CartRequest {
    private Long userId;
    private Long courseId;

}
