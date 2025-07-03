package com.example.alertsystem.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true)
    private String email;

    private String password;

    private String role = "USER";

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<EmergencyContact> contacts;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<IncidentReport> reports;

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<EmergencyContact> getContacts() {
        return contacts;
    }

    public void setContacts(List<EmergencyContact> contacts) {
        this.contacts = contacts;
    }

    public List<IncidentReport> getReports() {
        return reports;
    }

    public void setReports(List<IncidentReport> reports) {
        this.reports = reports;
    }
}
