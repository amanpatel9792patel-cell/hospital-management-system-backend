package com.hospitalmanagement.repository;

import com.hospitalmanagement.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository
        extends JpaRepository<Appointment, Long> {
}