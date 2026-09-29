package com.hospitalmanagement.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "departments")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Department name is required")
    @Size(
            min = 2,
            max = 100,
            message = "Department name must be between 2 and 100 characters"
    )
    @Column(nullable = false, unique = true)
    private String name;

    @NotBlank(message = "Description is required")
    @Size(
            min = 5,
            max = 500,
            message = "Description must be between 5 and 500 characters"
    )
    private String description;

    @NotBlank(message = "Location is required")
    private String location;

    public Department() {
    }

    public Department(
            Long id,
            String name,
            String description,
            String location) {

        this.id = id;
        this.name = name;
        this.description = description;
        this.location = location;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}