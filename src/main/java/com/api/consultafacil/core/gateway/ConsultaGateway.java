package com.api.consultafacil.core.gateway;

import com.api.consultafacil.core.domain.Consulta;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ConsultaGateway {

  Consulta save(final Consulta consulta);

  Optional<Consulta> findById(final Integer id);

  List<Consulta> findByUbsAndData(final Integer ubsId, final LocalDate dataConsulta);

  Consulta update(final Consulta consulta);

  void deleteById(final Integer id);
}
