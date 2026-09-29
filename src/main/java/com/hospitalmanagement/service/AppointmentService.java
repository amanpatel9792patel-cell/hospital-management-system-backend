package com.hospitalmanagement.service;

import com.hospitalmanagement.entity.Appointment;
import com.hospitalmanagement.entity.Doctor;
import com.hospitalmanagement.entity.Patient;
import com.hospitalmanagement.repository.AppointmentRepository;
import com.hospitalmanagement.repository.DoctorRepository;
import com.hospitalmanagement.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;

    public AppointmentService(
            AppointmentRepository appointmentRepository,
            PatientRepository patientRepository,
            DoctorRepository doctorRepository) {

        this.appointmentRepository = appointmentRepository;
        this.patientRepository = patientRepository;
        this.doctorRepository = doctorRepository;
    }

    // =====================================================
    // ADD APPOINTMENT
    // =====================================================

    public Appointment addAppointment(
            Long patientId,
            Long doctorId,
            Appointment appointment) {

        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Patient not found with id: " + patientId
                        )
                );

        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Doctor not found with id: " + doctorId
                        )
                );

        appointment.setPatient(patient);
        appointment.setDoctor(doctor);

        return appointmentRepository.save(appointment);
    }

    // =====================================================
    // GET ALL APPOINTMENTS
    // =====================================================

    public List<Appointment> getAllAppointments() {

        return appointmentRepository.findAll();
    }

    // =====================================================
    // GET APPOINTMENT BY ID
    // =====================================================

    public Appointment getAppointmentById(Long id) {

        return appointmentRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Appointment not found with id: " + id
                        )
                );
    }

    // =====================================================
    // UPDATE APPOINTMENT
    // =====================================================

    public Appointment updateAppointment(
            Long id,
            Long patientId,
            Long doctorId,
            Appointment appointment) {

        Appointment existingAppointment =
                appointmentRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Appointment not found with id: " + id
                                )
                        );

        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Patient not found with id: " + patientId
                        )
                );

        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Doctor not found with id: " + doctorId
                        )
                );

        existingAppointment.setPatient(patient);
        existingAppointment.setDoctor(doctor);

        existingAppointment.setAppointmentDate(
                appointment.getAppointmentDate()
        );

        existingAppointment.setAppointmentTime(
                appointment.getAppointmentTime()
        );

        existingAppointment.setStatus(
                appointment.getStatus()
        );

        existingAppointment.setReason(
                appointment.getReason()
        );

        return appointmentRepository.save(existingAppointment);
    }

    // =====================================================
    // DELETE APPOINTMENT
    // =====================================================

    public void deleteAppointment(Long id) {

        Appointment appointment =
                appointmentRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Appointment not found with id: " + id
                                )
                        );

        appointmentRepository.delete(appointment);
    }
}