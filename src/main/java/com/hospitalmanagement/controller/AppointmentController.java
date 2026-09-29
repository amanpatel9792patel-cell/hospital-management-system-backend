package com.hospitalmanagement.controller;
import org.springframework.security.access.prepost.PreAuthorize;
import com.hospitalmanagement.entity.Appointment;
import com.hospitalmanagement.service.AppointmentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/appointments")
@PreAuthorize("hasRole('ADMIN')")
@CrossOrigin(origins = "*")
public class AppointmentController {

    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    // CREATE APPOINTMENT
    @PostMapping
    public ResponseEntity<Appointment> addAppointment(
            @RequestParam Long patientId,
            @RequestParam Long doctorId,
            @Valid @RequestBody Appointment appointment) {

        Appointment savedAppointment =
                appointmentService.addAppointment(
                        patientId,
                        doctorId,
                        appointment
                );

        return new ResponseEntity<>(
                savedAppointment,
                HttpStatus.CREATED
        );
    }

    // GET ALL APPOINTMENTS
    @GetMapping
    public ResponseEntity<List<Appointment>> getAllAppointments() {

        return ResponseEntity.ok(
                appointmentService.getAllAppointments()
        );
    }

    // GET APPOINTMENT BY ID
    @GetMapping("/{id}")
    public ResponseEntity<Appointment> getAppointmentById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                appointmentService.getAppointmentById(id)
        );
    }

    // UPDATE APPOINTMENT
    @PutMapping("/{id}")
    public ResponseEntity<Appointment> updateAppointment(
            @PathVariable Long id,
            @RequestParam Long patientId,
            @RequestParam Long doctorId,
            @Valid @RequestBody Appointment appointment) {

        Appointment updatedAppointment =
                appointmentService.updateAppointment(
                        id,
                        patientId,
                        doctorId,
                        appointment
                );

        return ResponseEntity.ok(updatedAppointment);
    }

    // DELETE APPOINTMENT
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAppointment(
            @PathVariable Long id) {

        appointmentService.deleteAppointment(id);

        return ResponseEntity.ok(
                "Appointment deleted successfully"
        );
    }
}