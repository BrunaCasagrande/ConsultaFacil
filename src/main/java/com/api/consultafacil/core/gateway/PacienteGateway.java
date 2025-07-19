package com.api.consultafacil.core.gateway;

import com.api.consultafacil.core.domain.Paciente;
import java.util.Optional;

public interface PacienteGateway {

  Paciente save(final Paciente paciente);

  Optional<Paciente> findByCpf(final String cpf);

  Paciente update(final Paciente paciente);

  void deleteByCpf(final String cpf);
}
