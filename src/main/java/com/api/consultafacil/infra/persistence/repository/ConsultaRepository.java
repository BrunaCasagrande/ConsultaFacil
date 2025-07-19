package com.api.consultafacil.infra.persistence.repository;

import com.api.consultafacil.infra.persistence.entity.ConsultaEntity;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsultaRepository extends JpaRepository<ConsultaEntity, Integer> {

  List<ConsultaEntity> findByUbsIdAndDataConsulta(final Integer ubsId, final LocalDate dataConsulta);
}
