package com.api.consultafacil.core.usecase;

import com.api.consultafacil.core.domain.Consulta;
import com.api.consultafacil.core.gateway.ConsultaGateway;
import com.api.consultafacil.core.usecase.exception.ConsultaJaExisteException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreateConsulta {

  private final ConsultaGateway consultaGateway;

  public Consulta execute(final Consulta request) {

    final var consultasDoDia =
            consultaGateway.findByUbsAndData(
                    request.getUbs().getId(), request.getDataConsulta());

    final boolean pacienteJaTemConsulta =
            consultasDoDia.stream()
                    .anyMatch(
                            consulta ->
                                    consulta.getPaciente().getId().equals(request.getPaciente().getId()) &&
                                            consulta.getHoraConsulta().equals(request.getHoraConsulta()));

    if (pacienteJaTemConsulta) {
      throw new ConsultaJaExisteException(
              request.getPaciente().getId(), request.getDataConsulta(), request.getHoraConsulta());
    }

    final var novaConsulta = Consulta.createConsulta(
            request.getDataConsulta(),
            request.getHoraConsulta(),
            request.getUbs(),
            request.getPaciente());

    return consultaGateway.save(novaConsulta);
  }
}
