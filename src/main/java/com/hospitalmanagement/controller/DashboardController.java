package com.hospitalmanagement.controller;

import com.hospitalmanagement.repository.AppointmentRepository;
import com.hospitalmanagement.repository.DepartmentRepository;
import com.hospitalmanagement.repository.DoctorRepository;
import com.hospitalmanagement.repository.PatientRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;
    private final AppointmentRepository appointmentRepository;
    private final DepartmentRepository departmentRepository;

    public DashboardController(
            PatientRepository patientRepository,
            DoctorRepository doctorRepository,
            AppointmentRepository appointmentRepository,
            DepartmentRepository departmentRepository) {

        this.patientRepository = patientRepository;
        this.doctorRepository = doctorRepository;
        this.appointmentRepository = appointmentRepository;
        this.departmentRepository = departmentRepository;
    }

    @GetMapping
    public Map<String, Long> getDashboardData() {

        Map<String, Long> dashboard = new HashMap<>();

        dashboard.put(
                "totalPatients",
                patientRepository.count()
        );

        dashboard.put(
                "totalDoctors",
                doctorRepository.count()
        );

        dashboard.put(
                "totalAppointments",
                appointmentRepository.count()
        );

        dashboard.put(
                "totalDepartments",
                departmentRepository.count()
        );

        return dashboard;
    }
}