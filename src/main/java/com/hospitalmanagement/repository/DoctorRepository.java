package com.hospitalmanagement.repository;

import com.hospitalmanagement.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    List<Doctor> findBySpecializationIgnoreCase(String specialization);

    List<Doctor> findByDepartmentIgnoreCase(String department);
}