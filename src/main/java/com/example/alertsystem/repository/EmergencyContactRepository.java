package com.example.alertsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.alertsystem.model.EmergencyContact;

import java.util.List;

public interface EmergencyContactRepository extends JpaRepository<EmergencyContact, Long> {
    List<EmergencyContact> findByUserId(Long userId);
}
