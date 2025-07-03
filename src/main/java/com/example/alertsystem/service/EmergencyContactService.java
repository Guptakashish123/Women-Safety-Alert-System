package com.example.alertsystem.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.alertsystem.model.EmergencyContact;
import com.example.alertsystem.repository.EmergencyContactRepository;

import java.util.List;

@Service
public class EmergencyContactService {

    @Autowired
    private EmergencyContactRepository contactRepository;

    public List<EmergencyContact> getContactsByUser(Long userId) {
        return contactRepository.findByUserId(userId);
    }

    public EmergencyContact saveContact(EmergencyContact contact) {
        return contactRepository.save(contact);
    }

    public void deleteContact(Long id) {
        contactRepository.deleteById(id);
    }

    public EmergencyContact getContactById(Long id) {
        return contactRepository.findById(id).orElse(null);
    }
}
