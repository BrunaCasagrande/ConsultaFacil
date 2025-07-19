package com.api.consultafacil.core.usecase;

import com.api.consultafacil.core.domain.Consulta;
import com.api.consultafacil.core.dto.UpdateConsultaDto;
import com.api.consultafacil.core.gateway.ConsultaGateway;
import com.api.consultafacil.core.usecase.exception.ConsultaJaExisteException;
import com.api.consultafacil.core.usecase.exception.ConsultaNaoEncontradaException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UpdateConsulta {

  private final ConsultaGateway consultaGateway;

  public Consulta execute(final Integer id, final UpdateConsultaDto request) {

    final Consulta consultaExistente =
            consultaGateway.findById(id).orElseThrow(() -> new ConsultaNaoEncontradaException(id));

    if (request.getDataConsulta() != null && request.getHoraConsulta() != null) {
      final var consultasDoDia =
              consultaGateway.findByUbsAndData(
                      consultaExistente.getUbs().getId(), request.getDataConsulta());

      boolean pacienteJaTemOutraConsulta =
              consultasDoDia.stream()
                      .anyMatch(
                              consulta ->
                                      consulta.getPaciente().getId().equals(consultaExistente.getPaciente().getId())
                                              && consulta.getHoraConsulta().equals(request.getHoraConsulta())
                                              && !consulta.getId().equals(id));

      if (pacienteJaTemOutraConsulta) {
        throw new ConsultaJaExisteException(
                consultaExistente.getPaciente().getId(),
                request.getDataConsulta(),
                request.getHoraConsulta());
      }
    }

    if (request.getDataConsulta() != null) {
      consultaExistente.setDataConsulta(request.getDataConsulta());
    }
    if (request.getHoraConsulta() != null) {
      consultaExistente.setHoraConsulta(request.getHoraConsulta());
    }

    return consultaGateway.update(consultaExistente);
  }
}
