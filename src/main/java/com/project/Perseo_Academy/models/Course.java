package com.project.Perseo_Academy.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "course")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description", length = 1000)
    private String description;

    @Column(name = "price", nullable = false)
    private double price;

    @ManyToMany(mappedBy = "purchasedCourses")
    @JsonIgnore    // Evita que "users" aparezca en la respuesta JSON
    private Set<User> users = new HashSet<>();

    public Course(Long l, String javaBasics, String learnJavaFromScratch, double price) {}

    public  Course(Long id, String name, String description, Double price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
    }
}
