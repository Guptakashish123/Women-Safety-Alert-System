package com.example.alertsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.alertsystem.model.User;
import com.example.alertsystem.service.AlertService;
import com.example.alertsystem.service.UserService;

@Controller
public class DashboardController {

    @Autowired
    private UserService userService;

    @Autowired
    private AlertService alertService;

    @GetMapping("/dashboard")
    public String dashboard(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        User user = userService.findByEmail(userDetails.getUsername()).orElse(null);
        model.addAttribute("user", user);
        return "dashboard";
    }

    @PostMapping("/alert")
    public String sendAlert(@RequestParam double latitude,
                            @RequestParam double longitude,
                            @AuthenticationPrincipal UserDetails userDetails,
                            Model model) {

        User user = userService.findByEmail(userDetails.getUsername()).orElse(null);
        if (user != null) {
            alertService.sendAlert(user, latitude, longitude);
            model.addAttribute("msg", "Alert sent successfully");
        }
        return "redirect:/dashboard";
    }
}
