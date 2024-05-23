package com.br.mediwatch.services;

import com.br.mediwatch.errors.DoctorNotFoundException;
import com.br.mediwatch.models.DoctorModel;
import com.br.mediwatch.repositories.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class DoctorService {
    private final DoctorRepository doctorRepository;

    @Autowired
    public DoctorService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    public List<DoctorModel> getAllDoctors() {
        return doctorRepository.findAll();
    }

    public Optional<DoctorModel> getDoctorById(UUID id) {
        return doctorRepository.findById(id);
    }

    public DoctorModel createDoctor(DoctorModel doctor) {
        return doctorRepository.save(doctor);
    }

    public DoctorModel updateDoctor(UUID id, DoctorModel updateDoctor) {
        DoctorModel existingDoctor = doctorRepository.findById(id)
                .orElseThrow(() -> new DoctorNotFoundException("Doctor not found."));

        updateDoctor.setIdDoctor(existingDoctor.getIdDoctor());
        return doctorRepository.save(updateDoctor);
    }

    public void deleteDoctor(UUID id) {
        doctorRepository.deleteById(id);
    }
}
