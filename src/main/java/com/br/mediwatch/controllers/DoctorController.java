package com.br.mediwatch.controllers;

import com.br.mediwatch.errors.DoctorNotFoundException;
import com.br.mediwatch.models.DoctorModel;
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
public class DoctorController {
    private final PatientService patientService;
    private final DoctorService doctorService;
    private final ConsultationService consultationService;

    @Autowired
    public DoctorController(PatientService patientService, DoctorService doctorService, ConsultationService consultationService) {
        this.patientService = patientService;
        this.doctorService = doctorService;
        this.consultationService = consultationService;
    }

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
}
