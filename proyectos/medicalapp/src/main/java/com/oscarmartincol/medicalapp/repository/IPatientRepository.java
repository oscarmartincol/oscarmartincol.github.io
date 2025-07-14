package com.oscarmartincol.medicalapp.repository;

import com.oscarmartincol.medicalapp.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IPatientRepository extends JpaRepository<Patient, Long> {
    Optional<Patient> findByUsername(String username);
}
