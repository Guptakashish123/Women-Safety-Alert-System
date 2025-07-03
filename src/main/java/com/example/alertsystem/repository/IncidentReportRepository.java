package com.example.alertsystem.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.alertsystem.model.IncidentReport;

import java.util.List;

public interface IncidentReportRepository extends JpaRepository<IncidentReport, Long> {
    List<IncidentReport> findByUserId(Long userId);
}
