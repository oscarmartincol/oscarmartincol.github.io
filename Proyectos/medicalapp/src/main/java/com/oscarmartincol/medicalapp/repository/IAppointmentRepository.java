package com.oscarmartincol.medicalapp.repository;

import com.oscarmartincol.medicalapp.entity.Appointment;
import com.oscarmartincol.medicalapp.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.print.Doc;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface IAppointmentRepository extends JpaRepository<Appointment, Long> {

    List<Appointment> findByPatientUsername(String username);
    List<Appointment> findByDoctor_username(String username);
    boolean existsByDoctorAndDateTime(Doctor doctor, LocalDateTime dateTime);
}
