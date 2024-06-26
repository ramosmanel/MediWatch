package com.br.mediwatch.models;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "TB_CONSULTATIONS")
public class ConsultationModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String date;
    private String nameDoctor;
    private String namePatient;
    private String specialtyConsultation;
    private String status;
    private String locationConsultation;
    private String descriptionConsultation;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getNameDoctor() {
        return nameDoctor;
    }

    public void setNameDoctor(String nameDoctor) {
        this.nameDoctor = nameDoctor;
    }

    public String getNamePatient() {return namePatient;}

    public void setNamePatient(String namePatient) {this.namePatient = namePatient;}

    public String getSpecialtyConsultation() {
        return specialtyConsultation;
    }

    public void setSpecialtyConsultation(String specialtyConsultation) {
        this.specialtyConsultation = specialtyConsultation;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLocationConsultation() {
        return locationConsultation;
    }

    public void setLocationConsultation(String locationConsultation) {
        this.locationConsultation = locationConsultation;
    }

    public String getDescriptionConsultation() {
        return descriptionConsultation;
    }

    public void setDescriptionConsultation(String descriptionConsultation) {
        this.descriptionConsultation = descriptionConsultation;
    }
}
