package com.oscarmartincol.medicalapp.service;

import com.oscarmartincol.medicalapp.dto.AppointmentDTO;
import com.oscarmartincol.medicalapp.entity.Appointment;
import com.oscarmartincol.medicalapp.entity.Doctor;
import com.oscarmartincol.medicalapp.entity.Patient;
import com.oscarmartincol.medicalapp.repository.IAppointmentRepository;
import com.oscarmartincol.medicalapp.repository.IDoctorRepository;
import com.oscarmartincol.medicalapp.repository.IPatientRepository;
import com.oscarmartincol.medicalapp.repository.IUserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService {

    private final IUserRepository userRepository;
    private final IAppointmentRepository appointmentRepository;
    private final IDoctorRepository doctorRepository;
    private final IPatientRepository patientRepository;


    public AppointmentService(IUserRepository userRepository, IAppointmentRepository appointmentRepository, IDoctorRepository doctorRepository, IPatientRepository patientRepository) {
        this.userRepository = userRepository;
        this.appointmentRepository = appointmentRepository;
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
    }


    public void create(AppointmentDTO appointmentDTO, String username) {

        Patient patient = patientRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado."));

        Doctor doctor = doctorRepository.findById(appointmentDTO.doctorId).orElseThrow(() -> new RuntimeException("Doctor no encontrado."));

        if (appointmentRepository.existsByDoctorAndDateTime(doctor, appointmentDTO.dateTime)){
         throw new RuntimeException("El doctor ya tiene una cita asignada en este horario.");
        }

        Appointment app = new Appointment();
        app.setPatient(patient);
        app.setDoctor(doctor);
        app.setDateTime(appointmentDTO.dateTime);
        app.setReason(appointmentDTO.reason);
        appointmentRepository.save(app);
    }

    /* Appointments for patient*/
    public List<Appointment> getForPatient(String username) {
        return appointmentRepository.findByPatientUsername(username);
    }

    /* Appointments for doctor*/
    public List<Appointment> getForDoctor(String username) {
        return appointmentRepository.findByDoctor_username(username);
    }

    /* Delete appointment*/
    public void delete(Long id, String username) {
        Appointment app = appointmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cita no encontrada"));
        if (!app.getPatient().getUsername().equals(username)) {
            throw new RuntimeException("No puedes cancelar esta cita");
        }
        appointmentRepository.delete(app);
    }

}
