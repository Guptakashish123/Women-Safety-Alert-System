package com.example.alertsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.alertsystem.model.IncidentReport;
import com.example.alertsystem.service.IncidentReportService;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private IncidentReportService reportService;

    @GetMapping("/reports")
    public String viewReports(Model model) {
        List<IncidentReport> reports = reportService.getAllReports();
        model.addAttribute("reports", reports);
        return "admin";
    }
}

