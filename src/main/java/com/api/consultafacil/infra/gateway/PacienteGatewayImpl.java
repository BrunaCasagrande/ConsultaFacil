package com.api.consultafacil.infra.gateway;

import static java.lang.String.format;

import com.api.consultafacil.core.domain.Paciente;
import com.api.consultafacil.core.gateway.PacienteGateway;
import com.api.consultafacil.infra.gateway.exception.GatewayException;
import com.api.consultafacil.infra.persistence.entity.PacienteEntity;
import com.api.consultafacil.infra.persistence.repository.PacienteRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PacienteGatewayImpl implements PacienteGateway {

  private static final String MENSAGEM_ERRO_BUSCA = "Paciente com CPF=[%s] não encontrado.";

  private final PacienteRepository pacienteRepository;

  @Override
  public Paciente save(final Paciente paciente) {
    final var entity = PacienteEntity.builder()
            .nome(paciente.getNome())
            .cpf(paciente.getCpf())
            .cartaoSus(paciente.getCartaoSus())
            .telefone(paciente.getTelefone())
            .email(paciente.getEmail())
            .senha(paciente.getSenha())
            .build();

    final var salvo = pacienteRepository.save(entity);
    return toResponse(salvo);
  }

  @Override
  public Optional<Paciente> findByCpf(final String cpf) {
    return pacienteRepository.findByCpf(cpf).map(this::toResponse);
  }

  @Override
  public Paciente update(final Paciente paciente) {
    final var entity = pacienteRepository.findByCpf(paciente.getCpf())
            .orElseThrow(() -> new GatewayException(format(MENSAGEM_ERRO_BUSCA, paciente.getCpf())));

    entity.setNome(paciente.getNome());
    entity.setTelefone(paciente.getTelefone());
    entity.setEmail(paciente.getEmail());
    entity.setSenha(paciente.getSenha());

    final var atualizado = pacienteRepository.save(entity);
    return toResponse(atualizado);
  }

  @Override
  public void deleteByCpf(final String cpf) {
    pacienteRepository.deleteByCpf(cpf);
  }

  private Paciente toResponse(final PacienteEntity entity) {
    return Paciente.builder()
            .id(entity.getId())
            .nome(entity.getNome())
            .cpf(entity.getCpf())
            .cartaoSus(entity.getCartaoSus())
            .telefone(entity.getTelefone())
            .email(entity.getEmail())
            .senha(entity.getSenha())
            .build();
  }
}
