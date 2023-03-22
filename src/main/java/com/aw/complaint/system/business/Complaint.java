package com.aw.complaint.system.business;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
public class Complaint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String place;
    private String description;
    private LocalDateTime registeredDateTime;

    @Enumerated(EnumType.STRING)
    private Status status=Status.SUBMITTED;

    @Transient
    private String formattedDateTime;

    @ManyToOne
    private Client client;

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }


    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDateTime getRegisteredDateTime() {
        return registeredDateTime;
    }

    public void setRegisteredDateTime(LocalDateTime registeredDateTime) {
        this.registeredDateTime = registeredDateTime;
    }

    public Complaint() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Complaint(String place, String description) {
        this.place = place;
        this.description = description;
    }

    public String getFormattedDateTime() {
        DateTimeFormatter formatType = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        return registeredDateTime.format(formatType);
    }
}
