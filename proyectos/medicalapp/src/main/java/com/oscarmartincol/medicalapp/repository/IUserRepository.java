package com.oscarmartincol.medicalapp.repository;

import com.oscarmartincol.medicalapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IUserRepository extends JpaRepository<User, Long> {
    Optional<User> findByusername(String username);
}
