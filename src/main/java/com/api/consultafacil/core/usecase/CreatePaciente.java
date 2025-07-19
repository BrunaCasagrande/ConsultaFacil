package com.api.consultafacil.core.usecase;

import com.api.consultafacil.core.domain.Paciente;
import com.api.consultafacil.core.gateway.PacienteGateway;
import com.api.consultafacil.core.usecase.exception.PacienteJaExisteException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreatePaciente {

  private final PacienteGateway pacienteGateway;

  public Paciente execute(final Paciente request) {
    final var paciente = pacienteGateway.findByCpf(request.getCpf());

    if (paciente.isPresent()) {
      throw new PacienteJaExisteException(request.getCpf());
    }

    final var novoPaciente =
            Paciente.createPaciente(
                    request.getNome(),
                    request.getCpf(),
                    request.getCartaoSus(),
                    request.getTelefone(),
                    request.getEmail(),
                    request.getSenha());

    return pacienteGateway.save(novoPaciente);
  }
}
