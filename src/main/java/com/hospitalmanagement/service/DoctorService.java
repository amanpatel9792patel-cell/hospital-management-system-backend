package com.hospitalmanagement.service;

import com.hospitalmanagement.entity.Doctor;
import com.hospitalmanagement.repository.DoctorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorService {

    private final DoctorRepository doctorRepository;

    public DoctorService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    // CREATE
    public Doctor addDoctor(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    // GET ALL
    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    // GET BY ID
    public Doctor getDoctorById(Long id) {

        return doctorRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Doctor not found with id: " + id
                        ));
    }

    // UPDATE
    public Doctor updateDoctor(Long id, Doctor doctor) {

        Doctor existingDoctor = getDoctorById(id);

        existingDoctor.setName(doctor.getName());
        existingDoctor.setSpecialization(doctor.getSpecialization());
        existingDoctor.setPhone(doctor.getPhone());
        existingDoctor.setEmail(doctor.getEmail());
        existingDoctor.setDepartment(doctor.getDepartment());
        existingDoctor.setExperience(doctor.getExperience());

        return doctorRepository.save(existingDoctor);
    }

    // DELETE
    public void deleteDoctor(Long id) {

        Doctor doctor = getDoctorById(id);

        doctorRepository.delete(doctor);
    }

    // SEARCH BY SPECIALIZATION
    public List<Doctor> searchBySpecialization(String specialization) {

        return doctorRepository
                .findBySpecializationIgnoreCase(specialization);
    }

    // SEARCH BY DEPARTMENT
    public List<Doctor> searchByDepartment(String department) {

        return doctorRepository
                .findByDepartmentIgnoreCase(department);
    }
}