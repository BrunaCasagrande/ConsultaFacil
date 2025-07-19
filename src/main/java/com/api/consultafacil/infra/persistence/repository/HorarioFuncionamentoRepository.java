package com.api.consultafacil.infra.persistence.repository;

import com.api.consultafacil.infra.persistence.entity.HorarioFuncionamentoEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HorarioFuncionamentoRepository extends JpaRepository<HorarioFuncionamentoEntity, Integer> {

  List<HorarioFuncionamentoEntity> findByUbsId(final int ubsId);
}
