package com.hospitalmanagement.repository;

import com.hospitalmanagement.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository
        extends JpaRepository<Patient, Long> {

}