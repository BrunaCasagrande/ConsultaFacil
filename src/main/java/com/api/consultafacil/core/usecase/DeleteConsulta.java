package com.api.consultafacil.core.usecase;

import com.api.consultafacil.core.gateway.ConsultaGateway;
import com.api.consultafacil.core.usecase.exception.ConsultaNaoEncontradaException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeleteConsulta {

  private final ConsultaGateway consultaGateway;

  public void execute(final Integer id) {
    consultaGateway.findById(id).orElseThrow(() -> new ConsultaNaoEncontradaException(id));

    consultaGateway.deleteById(id);
  }
}
