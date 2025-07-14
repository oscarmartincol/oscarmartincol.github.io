package com.oscarmartincol.medicalapp.repository;

import com.oscarmartincol.medicalapp.entity.Doctor;
import org.springframework.context.annotation.ReflectiveScan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IDoctorRepository extends JpaRepository<Doctor, Long> {
    @Override
    Optional<Doctor> findById(Long id);
}
