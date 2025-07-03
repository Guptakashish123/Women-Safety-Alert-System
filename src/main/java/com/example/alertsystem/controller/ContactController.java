package com.example.alertsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.alertsystem.model.EmergencyContact;
import com.example.alertsystem.model.User;
import com.example.alertsystem.service.EmergencyContactService;
import com.example.alertsystem.service.UserService;

import java.util.List;

@Controller
@RequestMapping("/contacts")
public class ContactController {

    @Autowired
    private EmergencyContactService contactService;

    @Autowired
    private UserService userService;

    @GetMapping
    public String viewContacts(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        User user = userService.findByEmail(userDetails.getUsername()).orElse(null);
        List<EmergencyContact> contacts = contactService.getContactsByUser(user.getId());

        model.addAttribute("contacts", contacts);
        model.addAttribute("newContact", new EmergencyContact());
        return "contacts";
    }

    @PostMapping("/add")
    public String addContact(@ModelAttribute EmergencyContact newContact,
                             @AuthenticationPrincipal UserDetails userDetails) {
        User user = userService.findByEmail(userDetails.getUsername()).orElse(null);
        newContact.setUser(user);

        // ✅ Role is already mapped in the model via @ModelAttribute
        contactService.saveContact(newContact);
        return "redirect:/contacts";
    }

    @GetMapping("/delete/{id}")
    public String deleteContact(@PathVariable Long id) {
        contactService.deleteContact(id);
        return "redirect:/contacts";
    }
}

