package com.project.Perseo_Academy.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "carts")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

//    @ManyToMany
//    @JoinTable(
//            name = "cart_courses",
//            joinColumns = @JoinColumn(name = "cart_id"),
//            inverseJoinColumns = @JoinColumn(name = "course_id")
//    )
//    private List<Course> courses;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;


}


