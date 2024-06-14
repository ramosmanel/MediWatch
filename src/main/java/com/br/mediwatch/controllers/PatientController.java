package com.br.mediwatch.controllers;

import com.br.mediwatch.errors.PatientNotFoundException;
import com.br.mediwatch.models.PatientModel;
import com.br.mediwatch.services.ConsultationService;
import com.br.mediwatch.services.DoctorService;
import com.br.mediwatch.services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.BeanUtils;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/mediwatch")
public class PatientController {
    private final PatientService patientService;
    private final DoctorService doctorService;
    private final ConsultationService consultationService;

    @Autowired
    public PatientController(PatientService patientService, DoctorService doctorService, ConsultationService consultationService) {
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
}

