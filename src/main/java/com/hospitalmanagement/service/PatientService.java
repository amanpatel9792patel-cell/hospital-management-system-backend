package com.hospitalmanagement.service;

import com.hospitalmanagement.entity.Patient;
import com.hospitalmanagement.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService {

    private final PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public Patient addPatient(Patient patient) {
        return patientRepository.save(patient);
    }

    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    public Patient getPatientById(Long id) {
        return patientRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Patient not found with id: " + id
                        )
                );
    }

    public Patient updatePatient(Long id, Patient updatedPatient) {

        Patient existingPatient =
                patientRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException("Patient not found"));

        existingPatient.setName(updatedPatient.getName());
        existingPatient.setAge(updatedPatient.getAge());
        existingPatient.setGender(updatedPatient.getGender());
        existingPatient.setPhone(updatedPatient.getPhone());
        existingPatient.setDisease(updatedPatient.getDisease());

        return patientRepository.save(existingPatient);
    }

    public void deletePatient(Long id) {

        if (!patientRepository.existsById(id)) {
            throw new RuntimeException("Patient not found");
        }

        patientRepository.deleteById(id);
    }
}