package com.oscarmartincol.medicalapp.controller;

import com.oscarmartincol.medicalapp.dto.AppointmentDTO;
import com.oscarmartincol.medicalapp.entity.Appointment;
import com.oscarmartincol.medicalapp.service.AppointmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {

    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    /* Appointments for patient */
    @GetMapping("/patient")
    public List<Appointment> getForPatient(@AuthenticationPrincipal UserDetails user) {
        return appointmentService.getForPatient(user.getUsername());
    }






























    /* Appointments for doctor */
    @GetMapping("/doctor")
    public List<Appointment> getForDoctor(@AuthenticationPrincipal UserDetails user) {
        return appointmentService.getForDoctor(user.getUsername());
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody AppointmentDTO dto, @AuthenticationPrincipal UserDetails user) {
        appointmentService.create(dto, user.getUsername());
        return  ResponseEntity.ok("Cita registrada con éxito");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id, @AuthenticationPrincipal UserDetails user) {
        appointmentService.delete(id, user.getUsername());
        return  ResponseEntity.ok("Cita cancelada");
    }
}
