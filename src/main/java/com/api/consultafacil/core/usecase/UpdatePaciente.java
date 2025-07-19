package com.api.consultafacil.core.usecase;

import com.api.consultafacil.core.domain.Paciente;
import com.api.consultafacil.core.dto.UpdatePacienteDto;
import com.api.consultafacil.core.gateway.PacienteGateway;
import com.api.consultafacil.core.usecase.exception.PacienteNaoEncontradoException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UpdatePaciente {

  private final PacienteGateway pacienteGateway;

  public Paciente execute(final String cpf, final UpdatePacienteDto request) {
    final Paciente pacienteExistente =
            pacienteGateway.findByCpf(cpf).orElseThrow(() -> new PacienteNaoEncontradoException(cpf));

    pacienteExistente.setNome(request.getNome());
    pacienteExistente.setTelefone(request.getTelefone());
    pacienteExistente.setEmail(request.getEmail());
    pacienteExistente.setSenha(request.getSenha());

    return pacienteGateway.update(pacienteExistente);
  }
}
