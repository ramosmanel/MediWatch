package com.br.mediwatch.controllers;

import com.br.mediwatch.errors.PatientNotFoundException;
import com.br.mediwatch.models.ConsultationModel;
import com.br.mediwatch.services.ConsultationService;
import com.br.mediwatch.services.DoctorService;
import com.br.mediwatch.services.PatientService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/mediwatch")
public class ConsultationController {
    private final PatientService patientService;
    private final DoctorService doctorService;
    private final ConsultationService consultationService;

    @Autowired
    public ConsultationController(PatientService patientService, DoctorService doctorService, ConsultationService consultationService) {
        this.patientService = patientService;
        this.doctorService = doctorService;
        this.consultationService = consultationService;
    }

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
