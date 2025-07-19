package com.api.consultafacil.infra.persistence.repository;

import com.api.consultafacil.infra.persistence.entity.PacienteEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteRepository extends JpaRepository<PacienteEntity, Integer> {

  Optional<PacienteEntity> findByCpf(final String cpf);

  void deleteByCpf(final String cpf);
}
