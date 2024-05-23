package com.br.mediwatch.models;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "TB_DOCTORS")
public class DoctorModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idDoctor;
    private String nameDoctor;
    private String sspecialtyDoctor;
    private String crmIdentification;

    public UUID getIdDoctor() {
        return idDoctor;
    }

    public void setIdDoctor(UUID idDoctor) {
        this.idDoctor = idDoctor;
    }

    public String getNameDoctor() {
        return nameDoctor;
    }

    public void setNameDoctor(String nameDoctor) {
        this.nameDoctor = nameDoctor;
    }

    public String getSspecialtyDoctor() {
        return sspecialtyDoctor;
    }

    public void setSspecialtyDoctor(String sspecialtyDoctor) {
        this.sspecialtyDoctor = sspecialtyDoctor;
    }

    public String getCrmIdentification() {
        return crmIdentification;
    }

    public void setCrmIdentification(String crmIdentification) {
        this.crmIdentification = crmIdentification;
    }
}
