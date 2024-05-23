package com.br.mediwatch.repositories;

import com.br.mediwatch.models.ConsultationModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface ConsultationRepository extends JpaRepository<ConsultationModel, UUID> {
}
