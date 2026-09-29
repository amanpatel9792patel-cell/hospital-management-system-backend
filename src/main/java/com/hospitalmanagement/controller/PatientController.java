package com.hospitalmanagement.controller;
import org.springframework.security.access.prepost.PreAuthorize;
import com.hospitalmanagement.entity.Patient;
import com.hospitalmanagement.service.PatientService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patients")
@PreAuthorize("hasRole('ADMIN')")
@CrossOrigin
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    // CREATE
    @PostMapping
    public Patient addPatient(@Valid @RequestBody Patient patient) {
        return patientService.addPatient(patient);
    }

    // GET ALL
    @GetMapping
    public List<Patient> getAllPatients() {
        return patientService.getAllPatients();
    }

    // GET BY ID
    @GetMapping("/{id}")
    public Patient getPatientById(@PathVariable Long id) {
        return patientService.getPatientById(id);
    }

    // UPDATE
    @PutMapping("/{id}")
    public Patient updatePatient(
            @PathVariable Long id,
            @Valid @RequestBody Patient patient) {

        return patientService.updatePatient(id, patient);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePatient(
            @PathVariable Long id) {

        patientService.deletePatient(id);

        return ResponseEntity.ok(
                "Patient deleted successfully"
        );
    }
}