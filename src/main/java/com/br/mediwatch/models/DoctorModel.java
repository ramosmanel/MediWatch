package com.br.mediwatch.models;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "TB_DOCTORS")
public class DoctorModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idDoctor;
    private String nameDoctor;
    private String specialtyDoctor;
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

    public String getSpecialtyDoctor() {
        return specialtyDoctor;
    }

    public void setSpecialtyDoctor(String specialtyDoctor) {
        this.specialtyDoctor = specialtyDoctor;
    }

    public String getCrmIdentification() {
        return crmIdentification;
    }

    public void setCrmIdentification(String crmIdentification) {
        this.crmIdentification = crmIdentification;
    }
}
