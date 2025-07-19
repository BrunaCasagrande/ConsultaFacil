package com.api.consultafacil.infra.gateway;

import static java.lang.String.format;

import com.api.consultafacil.core.domain.Consulta;
import com.api.consultafacil.core.dto.PacienteDto;
import com.api.consultafacil.core.dto.UBSDto;
import com.api.consultafacil.core.gateway.ConsultaGateway;
import com.api.consultafacil.infra.gateway.exception.GatewayException;
import com.api.consultafacil.infra.persistence.entity.ConsultaEntity;
import com.api.consultafacil.infra.persistence.entity.PacienteEntity;
import com.api.consultafacil.infra.persistence.entity.UBSEntity;
import com.api.consultafacil.infra.persistence.repository.ConsultaRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ConsultaGatewayImpl implements ConsultaGateway {

  private static final String FIND_ERROR_MESSAGE = "Consulta com ID=[%d] não encontrada.";

  private final ConsultaRepository consultaRepository;

  @Override
  public Consulta save(final Consulta consulta) {
    final var entity = ConsultaEntity.builder()
            .dataConsulta(consulta.getDataConsulta())
            .horaConsulta(consulta.getHoraConsulta())
            .ubs(toUbsEntity(consulta.getUbs()))
            .paciente(toPacienteEntity(consulta.getPaciente()))
            .build();

    final var saved = consultaRepository.save(entity);
    return toConsultaDomain(saved);
  }

  @Override
  public Optional<Consulta> findById(final Integer id) {
    return consultaRepository.findById(id).map(this::toConsultaDomain);
  }

  @Override
  public List<Consulta> findByUbsAndData(final Integer ubsId, final LocalDate dataConsulta) {
    return consultaRepository.findByUbsIdAndDataConsulta(ubsId, dataConsulta)
            .stream()
            .map(this::toConsultaDomain)
            .toList();
  }

  @Override
  public Consulta update(final Consulta consulta) {
    final var entity = consultaRepository.findById(consulta.getId())
            .orElseThrow(() -> new GatewayException(format(FIND_ERROR_MESSAGE, consulta.getId())));

    entity.setDataConsulta(consulta.getDataConsulta());
    entity.setHoraConsulta(consulta.getHoraConsulta());

    final var updated = consultaRepository.save(entity);
    return toConsultaDomain(updated);
  }

  @Override
  public void deleteById(final Integer id) {
    consultaRepository.deleteById(id);
  }

  private Consulta toConsultaDomain(final ConsultaEntity entity) {
    return Consulta.builder()
            .id(entity.getId())
            .dataConsulta(entity.getDataConsulta())
            .horaConsulta(entity.getHoraConsulta())
            .ubs(toUbsDto(entity.getUbs()))
            .paciente(toPacienteDto(entity.getPaciente()))
            .build();
  }

  private UBSEntity toUbsEntity(final UBSDto dto) {
    return UBSEntity.builder()
            .id(dto.getId())
            .nome(dto.getNome())
            .cnpj(dto.getCnpj())
            .endereco(dto.getEndereco())
            .cidade(dto.getCidade())
            .telefone(dto.getTelefone())
            .email(dto.getEmail())
            .build();
  }

  private UBSDto toUbsDto(final UBSEntity entity) {
    return UBSDto.builder()
            .id(entity.getId())
            .nome(entity.getNome())
            .cnpj(entity.getCnpj())
            .endereco(entity.getEndereco())
            .cidade(entity.getCidade())
            .telefone(entity.getTelefone())
            .email(entity.getEmail())
            .build();
  }

  private PacienteEntity toPacienteEntity(final PacienteDto dto) {
    return PacienteEntity.builder()
            .id(dto.getId())
            .nome(dto.getNome())
            .cpf(dto.getCpf())
            .cartaoSus(dto.getCartaoSus())
            .telefone(dto.getTelefone())
            .email(dto.getEmail())
            .senha(dto.getSenha())
            .build();
  }

  private PacienteDto toPacienteDto(final PacienteEntity entity) {
    return PacienteDto.builder()
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
