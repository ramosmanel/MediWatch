package com.br.mediwatch.repositories;

import com.br.mediwatch.models.DoctorModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface DoctorRepository extends JpaRepository<DoctorModel, UUID> {
    Optional<DoctorModel> findByNameDoctor(String nameDoctor);
}
