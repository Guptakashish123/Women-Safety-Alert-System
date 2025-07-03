package com.example.alertsystem.service;

import org.springframework.stereotype.Service;

import com.example.alertsystem.model.EmergencyContact;
import com.example.alertsystem.model.User;

import java.util.List;

@Service
public class AlertService {

    public void sendAlert(User user, double lat, double lon) {
        List<EmergencyContact> contacts = user.getContacts();
        if (contacts != null) {
            for (EmergencyContact contact : contacts) {
                System.out.println("Sending ALERT to: " + contact.getPhone()
                    + " | User: " + user.getName()
                    + " | Location: " + lat + ", " + lon);
            }
        }
    }
}
