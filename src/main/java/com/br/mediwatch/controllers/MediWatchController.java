package com.br.mediwatch.controllers;

import com.br.mediwatch.errors.DoctorNotFoundException;
import com.br.mediwatch.errors.PatientNotFoundException;
import com.br.mediwatch.models.ConsultationModel;
import com.br.mediwatch.models.DoctorModel;
import com.br.mediwatch.models.PatientModel;
import com.br.mediwatch.services.ConsultationService;
import com.br.mediwatch.services.DoctorService;
import com.br.mediwatch.services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.BeanUtils;


import javax.print.Doc;
import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/mediwatch")
public class MediWatchController {
    private final PatientService patientService;
    private final DoctorService doctorService;
    private final ConsultationService consultationService;

    @Autowired
    public MediWatchController(PatientService patientService, DoctorService doctorService, ConsultationService consultationService) {
        this.patientService = patientService;
        this.doctorService = doctorService;
        this.consultationService = consultationService;
    }

    //Patients
    @PostMapping("/patients")
    public ResponseEntity<PatientModel>  createPatient(@RequestBody PatientModel patient) {
        PatientModel newPatient = patientService.createPatient(patient);
        return new ResponseEntity<>(newPatient, HttpStatus.OK);
    }

    @GetMapping("/patients")
    public ResponseEntity<List<PatientModel>> getAllPatients() {
        List<PatientModel> patients = patientService.getAllPatients();
        return new ResponseEntity<>(patients, HttpStatus.OK);
    }

    @GetMapping("/patients/{id}")
    public ResponseEntity<PatientModel> getPatientsById(@PathVariable UUID id) {
        Optional<PatientModel> patient = patientService.getPatitentById(id);

        return patient.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/patients/{id}")
    public ResponseEntity<PatientModel> updatePatients(@PathVariable UUID id, @RequestBody PatientModel updatePatient) {
        Optional<PatientModel> existingPatientOptional = patientService.getPatitentById(id);
        if (existingPatientOptional.isPresent()) {
            PatientModel existingPatient = existingPatientOptional.get();
            BeanUtils.copyProperties(updatePatient, existingPatient, "idPatient");

            PatientModel updatedPatient = patientService.updatePatient(id, existingPatient);
            return ResponseEntity.ok(updatedPatient);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/patients/{id}")
    public ResponseEntity<?> deletePatient(@PathVariable UUID id) {
        try {
            patientService.deletePatient(id);
            return ResponseEntity.noContent().build();
        } catch (PatientNotFoundException message) {
            return ResponseEntity.notFound().build();
        }
    }

    //Doctors
    @PostMapping("/doctors")
    public ResponseEntity<DoctorModel> createDoctor(@RequestBody DoctorModel doctor) {
        DoctorModel newDoctor = doctorService.createDoctor(doctor);
        return new ResponseEntity<>(newDoctor, HttpStatus.OK);
    }

    @GetMapping("/doctors")
    public ResponseEntity<List<DoctorModel>> getAllDoctors() {
        List<DoctorModel> doctors = doctorService.getAllDoctors();
        return new ResponseEntity<>(doctors, HttpStatus.OK);
    }

    @GetMapping("/doctors/{id}")
    public ResponseEntity<DoctorModel> getDoctorById(@PathVariable UUID id) {
        Optional<DoctorModel> doctor = doctorService.getDoctorById(id);
        return doctor.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/doctors/{id}")
    public ResponseEntity<DoctorModel> updateDoctor(@PathVariable UUID id, @RequestBody DoctorModel updateDoctor) {
        Optional<DoctorModel> existingDoctorOptional = doctorService.getDoctorById(id);
        if (existingDoctorOptional.isPresent()) {
            DoctorModel existingDoctor = existingDoctorOptional.get();
            BeanUtils.copyProperties(updateDoctor, existingDoctor, "idDoctor");

            DoctorModel updatedDoctor = doctorService.updateDoctor(id, existingDoctor);
            return ResponseEntity.ok(updatedDoctor);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/doctors/{id}")
    public ResponseEntity<?> deleteDoctor(@PathVariable UUID id) {
        try {
            doctorService.deleteDoctor(id);
            return ResponseEntity.noContent().build();
        }catch (DoctorNotFoundException message) {
            return ResponseEntity.notFound().build();
        }
    }

    //Consultation
    @PostMapping("/consultation")
    public ResponseEntity<ConsultationModel> createConsultation(@RequestBody ConsultationModel consultation) {
        ConsultationModel newConsultation = consultationService.createConsultation(consultation);
        return new ResponseEntity<>(newConsultation, HttpStatus.OK);
    }

    @GetMapping("/consultation")
    public ResponseEntity<List<ConsultationModel>> getAllConsultation(){
        List<ConsultationModel> consultations = consultationService.getAllConsultation();
        return new ResponseEntity<>(consultations, HttpStatus.OK);
    }

    @GetMapping("/consultation/{id}")
    public ResponseEntity<ConsultationModel> getConsultationById(@PathVariable UUID id) {
        Optional<ConsultationModel> consultation = consultationService.getConsultationById(id);
        return consultation.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/consultation/{id}")
    public ResponseEntity<ConsultationModel> updateConsultation(@PathVariable UUID id, @RequestBody ConsultationModel updateConsultation){
        Optional<ConsultationModel> existingConsultationOptional = consultationService.getConsultationById(id);
        if(existingConsultationOptional.isPresent()) {
            ConsultationModel existingConsultation = existingConsultationOptional.get();
            BeanUtils.copyProperties(updateConsultation, existingConsultation, "idConsultation");

            ConsultationModel updatedConsultation = consultationService.updateConsultation(id, existingConsultation);
            return ResponseEntity.ok(updatedConsultation);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/consultation/{id}")
    public ResponseEntity<?> deleteConsultation(@PathVariable UUID id) {
        try {
            consultationService.deleteConsultation(id);
            return ResponseEntity.noContent().build();
        } catch (PatientNotFoundException message) {
            return ResponseEntity.noContent().build();
        }
    }
}

