package com.api.consultafacil.core.usecase;

import com.api.consultafacil.core.domain.Paciente;
import com.api.consultafacil.core.gateway.PacienteGateway;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SearchPaciente {

  private final PacienteGateway pacienteGateway;

  public Optional<Paciente> execute(final String cpf) {
    return pacienteGateway.findByCpf(cpf);
  }
}
