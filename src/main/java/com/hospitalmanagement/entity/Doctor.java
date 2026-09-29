package com.hospitalmanagement.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "doctors")
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Doctor name is required")
    @Size(min = 2, max = 100,
            message = "Doctor name must be between 2 and 100 characters")
    private String name;

    @NotBlank(message = "Specialization is required")
    private String specialization;

    @NotBlank(message = "Phone is required")
    @Pattern(
            regexp = "^[0-9]{10}$",
            message = "Phone must contain exactly 10 digits"
    )
    private String phone;

    @NotBlank(message = "Email is required")
    @Email(message = "Enter a valid email address")
    private String email;

    @NotBlank(message = "Department is required")
    private String department;

    @NotNull(message = "Experience is required")
    @Min(value = 0, message = "Experience cannot be negative")
    @Max(value = 60, message = "Experience cannot be more than 60 years")
    private Integer experience;

    public Doctor() {
    }

    public Doctor(
            Long id,
            String name,
            String specialization,
            String phone,
            String email,
            String department,
            Integer experience) {

        this.id = id;
        this.name = name;
        this.specialization = specialization;
        this.phone = phone;
        this.email = email;
        this.department = department;
        this.experience = experience;
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

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Integer getExperience() {
        return experience;
    }

    public void setExperience(Integer experience) {
        this.experience = experience;
    }
}