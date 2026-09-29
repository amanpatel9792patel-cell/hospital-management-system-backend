package com.hospitalmanagement.controller;
import org.springframework.security.access.prepost.PreAuthorize;
import com.hospitalmanagement.entity.Doctor;
import com.hospitalmanagement.service.DoctorService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/doctors")
@PreAuthorize("hasRole('ADMIN')")
@CrossOrigin
public class DoctorController {

    private final DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    // CREATE
    @PostMapping
    public Doctor addDoctor(
            @Valid @RequestBody Doctor doctor) {

        return doctorService.addDoctor(doctor);
    }

    // GET ALL
    @GetMapping
    public List<Doctor> getAllDoctors() {

        return doctorService.getAllDoctors();
    }

    // GET BY ID
    @GetMapping("/{id}")
    public Doctor getDoctorById(
            @PathVariable Long id) {

        return doctorService.getDoctorById(id);
    }

    // UPDATE
    @PutMapping("/{id}")
    public Doctor updateDoctor(
            @PathVariable Long id,
            @Valid @RequestBody Doctor doctor) {

        return doctorService.updateDoctor(id, doctor);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDoctor(
            @PathVariable Long id) {

        doctorService.deleteDoctor(id);

        return ResponseEntity.ok(
                "Doctor deleted successfully"
        );
    }

    // SEARCH SPECIALIZATION
    @GetMapping("/search/specialization")
    public List<Doctor> searchBySpecialization(
            @RequestParam String specialization) {

        return doctorService
                .searchBySpecialization(specialization);
    }

    // SEARCH DEPARTMENT
    @GetMapping("/search/department")
    public List<Doctor> searchByDepartment(
            @RequestParam String department) {

        return doctorService
                .searchByDepartment(department);
    }
}