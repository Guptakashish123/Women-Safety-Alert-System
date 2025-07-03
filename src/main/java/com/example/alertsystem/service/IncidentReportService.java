package com.example.alertsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.alertsystem.model.IncidentReport;
import com.example.alertsystem.repository.IncidentReportRepository;

import java.util.List;

@Service
public class IncidentReportService {

    @Autowired
    private IncidentReportRepository reportRepository;

    public IncidentReport submitReport(IncidentReport report) {
        return reportRepository.save(report);
    }

    public List<IncidentReport> getAllReports() {
        return reportRepository.findAll();
    }

    public List<IncidentReport> getReportsByUser(Long userId) {
        return reportRepository.findByUserId(userId);
    }
}
