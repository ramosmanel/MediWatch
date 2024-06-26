package com.br.mediwatch.services;

import com.br.mediwatch.errors.ConsultationNotFoundException;
import com.br.mediwatch.errors.DoctorNotFoundException;
import com.br.mediwatch.errors.InvalidDoctorSpecialtyException;
import com.br.mediwatch.errors.PatientNotFoundException;
import com.br.mediwatch.models.ConsultationModel;
import com.br.mediwatch.models.DoctorModel;
import com.br.mediwatch.models.PatientModel;
import com.br.mediwatch.repositories.ConsultationRepository;
import com.br.mediwatch.repositories.DoctorRepository;
import com.br.mediwatch.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ConsultationService {
    private final ConsultationRepository consultationRepository;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;

    @Autowired
    public ConsultationService(ConsultationRepository consultationRepository, DoctorRepository doctorRepository, PatientRepository patientRepository) {
        this.consultationRepository = consultationRepository;
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
    }

    public List<ConsultationModel> getAllConsultation() {
        return consultationRepository.findAll();
    }

    public Optional<ConsultationModel> getConsultationById(UUID id) {
        return  consultationRepository.findById(id);
    }

    public ConsultationModel createConsultation(ConsultationModel consultation) {
        DoctorModel doctor = doctorRepository.findByNameDoctor(consultation.getNameDoctor())
                .orElseThrow(() -> new DoctorNotFoundException("Doctor not found."));

        if (!doctor.getSpecialtyDoctor().equals(consultation.getSpecialtyConsultation())) {
            throw new InvalidDoctorSpecialtyException("The doctor is not an expert in the specialty provided.");
        }

        Optional<PatientModel> optionalPatient = patientRepository.findByNamePatient(consultation.getNamePatient());

        if(optionalPatient.isEmpty()) {
            throw new PatientNotFoundException("Patient not found.");
        }


        return consultationRepository.save(consultation);
    }

    public ConsultationModel updateConsultation(UUID id, ConsultationModel updateConsultation) {
        if (!consultationRepository.existsById(id)) {
            throw  new ConsultationNotFoundException("Consultation not found.");
        }

        DoctorModel doctor = doctorRepository.findByNameDoctor((updateConsultation.getNameDoctor()))
                .orElseThrow(() -> new DoctorNotFoundException("Doctor not found"));

        if (!doctor.getSpecialtyDoctor().equals(updateConsultation.getSpecialtyConsultation())) {
            throw  new InvalidDoctorSpecialtyException("The doctor is not an expert in the specialty provided.");
        }

        Optional<PatientModel> optionalPatient = patientRepository.findByNamePatient(updateConsultation.getNamePatient());

        if(optionalPatient.isEmpty()) {
            throw new PatientNotFoundException("Patient not found.");
        }

        updateConsultation.setId(id);
        return consultationRepository.save(updateConsultation);
    }

    public void deleteConsultation(UUID id) {
        consultationRepository.deleteById(id);
    }
}
