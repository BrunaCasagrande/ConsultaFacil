package com.api.consultafacil.core.usecase;

import com.api.consultafacil.core.domain.Consulta;
import com.api.consultafacil.core.gateway.ConsultaGateway;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SearchConsulta {

  private final ConsultaGateway consultaGateway;

  public Optional<Consulta> execute(final Integer id) {
    return consultaGateway.findById(id);
  }
}
