package com.br.mediwatch.services;

import com.br.mediwatch.errors.PatientNotFoundException;
import com.br.mediwatch.models.PatientModel;
import com.br.mediwatch.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PatientService {
    private final PatientRepository patientRepository;

    @Autowired
    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public List<PatientModel> getAllPatients() {
        return patientRepository.findAll();
    }

    public Optional<PatientModel> getPatitentById(UUID id) {
        return patientRepository.findById(id);
    }

    public PatientModel createPatient(PatientModel patient) {
        return patientRepository.save(patient);
    }

    public PatientModel updatePatient(UUID id, PatientModel updatePatient) {
        PatientModel existingPatient = patientRepository.findById(id)
                .orElseThrow(() -> new PatientNotFoundException("Patient not found."));

        updatePatient.setIdPatient(existingPatient.getIdPatient());
        return patientRepository.save(updatePatient);
    }

    public void deletePatient(UUID id) {
        patientRepository.deleteById(id);
    }
}
