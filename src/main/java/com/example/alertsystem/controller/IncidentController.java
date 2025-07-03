package com.example.alertsystem.controller;

//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//
//import com.example.alertsystem.model.IncidentReport;
//import com.example.alertsystem.model.User;
//import com.example.alertsystem.service.IncidentReportService;
//import com.example.alertsystem.service.UserService;
//
//import java.io.File;
//import java.io.IOException;
//import java.util.UUID;
//
//@Controller
//@RequestMapping("/report")
//public class IncidentController {
//
//    @Autowired
//    private IncidentReportService reportService;
//
//    @Autowired
//    private UserService userService;
//
//    private static final String UPLOAD_DIR = "uploads/";
//
//    @GetMapping
//    public String showReportForm(Model model) {
//        model.addAttribute("report", new IncidentReport());
//        return "report";
//    }
//
//    @PostMapping
//    public String submitReport(@ModelAttribute IncidentReport report,
//                                @RequestParam("file") MultipartFile file,
//                                @AuthenticationPrincipal UserDetails userDetails) throws IOException {
//
//        User user = userService.findByEmail(userDetails.getUsername()).orElse(null);
//        report.setUser(user);
//
//        if (!file.isEmpty()) {
//            String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
//            File dest = new File(UPLOAD_DIR + fileName);
//            file.transferTo(dest);
//            report.setImagePath("/" + UPLOAD_DIR + fileName);
//        }
//
//        reportService.submitReport(report);
//        return "redirect:/dashboard";
//    }
//}


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.example.alertsystem.model.IncidentReport;
import com.example.alertsystem.model.User;
import com.example.alertsystem.service.IncidentReportService;
import com.example.alertsystem.service.UserService;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Controller
@RequestMapping("/report")
public class IncidentController {

    @Autowired
    private IncidentReportService reportService;

    @Autowired
    private UserService userService;

    // Absolute path to project-root/uploads/
    private static final String UPLOAD_DIR = System.getProperty("user.dir") + "/uploads/";

    @GetMapping
    public String showReportForm(Model model) {
        model.addAttribute("report", new IncidentReport());
        return "report";
    }

    @PostMapping
    public String submitReport(@ModelAttribute IncidentReport report,
                                @RequestParam("file") MultipartFile file,
                                @AuthenticationPrincipal UserDetails userDetails) throws IOException {

        User user = userService.findByEmail(userDetails.getUsername()).orElse(null);
        report.setUser(user);

        if (!file.isEmpty()) {
            // Ensure upload directory exists
            File uploadFolder = new File(UPLOAD_DIR);
            if (!uploadFolder.exists()) {
                uploadFolder.mkdirs();
            }

            String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
            File dest = new File(uploadFolder, fileName);
            file.transferTo(dest);

            // Save path relative to /uploads so it can be accessed via static path
            report.setImagePath("/uploads/" + fileName);
        }

        reportService.submitReport(report);
        return "redirect:/dashboard";
    }
}
