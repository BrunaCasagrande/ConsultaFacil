package com.api.consultafacil.core.usecase;

import com.api.consultafacil.core.gateway.PacienteGateway;
import com.api.consultafacil.core.usecase.exception.PacienteNaoEncontradoException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Transactional
public class DeletePaciente {

  private final PacienteGateway pacienteGateway;

  public void execute(final String cpf) {
    pacienteGateway.findByCpf(cpf).orElseThrow(() -> new PacienteNaoEncontradoException(cpf));
    pacienteGateway.deleteByCpf(cpf);
  }
}
