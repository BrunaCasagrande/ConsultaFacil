package com.api.consultafacil.infra.gateway;

import static java.lang.String.format;

import com.api.consultafacil.core.domain.UBS;
import com.api.consultafacil.core.dto.HorarioFuncionamentoDto;
import com.api.consultafacil.core.gateway.UBSGateway;
import com.api.consultafacil.infra.gateway.exception.GatewayException;
import com.api.consultafacil.infra.persistence.entity.HorarioFuncionamentoEntity;
import com.api.consultafacil.infra.persistence.entity.UBSEntity;
import com.api.consultafacil.infra.persistence.repository.HorarioFuncionamentoRepository;
import com.api.consultafacil.infra.persistence.repository.UBSRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class UBSGatewayImpl implements UBSGateway {

  private static final String FIND_ERROR_MESSAGE = "UBS com CNPJ=[%s] não encontrada.";

  private final HorarioFuncionamentoRepository horarioFuncionamentoRepository;
  private final UBSRepository ubsRepository;

  @Override
  public UBS save(final UBS ubs) {
    final var entity =
            UBSEntity.builder()
                    .nome(ubs.getNome())
                    .cnpj(ubs.getCnpj())
                    .endereco(ubs.getEndereco())
                    .cidade(ubs.getCidade())
                    .telefone(ubs.getTelefone())
                    .email(ubs.getEmail())
                    .senha(ubs.getSenha())
                    .build();

    final var savedUbs = ubsRepository.save(entity);
    final var horarios = toHorarioEntity(ubs.getHorariosFuncionamento(), savedUbs);

    horarioFuncionamentoRepository.saveAll(horarios);
    savedUbs.setHorariosFuncionamento(horarios);

    return this.toResponse(savedUbs);
  }

  @Override
  public Optional<UBS> findByCnpj(final String cnpj) {
    return ubsRepository.findByCnpj(cnpj).map(this::toResponse);
  }

  @Override
  public UBS update(final UBS ubs) {
    try {
      final var entity =
              ubsRepository
                      .findByCnpj(ubs.getCnpj())
                      .orElseThrow(() -> new GatewayException(format(FIND_ERROR_MESSAGE, ubs.getCnpj())));

      entity.setEndereco(ubs.getEndereco());
      entity.setTelefone(ubs.getTelefone());
      entity.setEmail(ubs.getEmail());

      final var updatedEntity = ubsRepository.save(entity);
      return this.toResponse(updatedEntity);
    } catch (IllegalArgumentException e) {
      throw new GatewayException(format(FIND_ERROR_MESSAGE, ubs.getCnpj()));
    }
  }

  @Override
  public void deleteByCnpj(final String cnpj) {
    ubsRepository.deleteByCnpj(cnpj);
  }

  private List<HorarioFuncionamentoEntity> toHorarioEntity(
          final List<HorarioFuncionamentoDto> dtos, final UBSEntity ubsEntity) {
    return dtos.stream()
            .map(dto ->
                    HorarioFuncionamentoEntity.builder()
                            .diaSemana(dto.getDiaSemana())
                            .horarioAbertura(dto.getHorarioAbertura())
                            .horarioFechamento(dto.getHorarioFechamento())
                            .ubs(ubsEntity)
                            .build())
            .toList();
  }

  private List<HorarioFuncionamentoDto> toHorarioDto(final List<HorarioFuncionamentoEntity> entities) {
    return entities.stream()
            .map(entity ->
                    HorarioFuncionamentoDto.builder()
                            .id(entity.getId())
                            .diaSemana(entity.getDiaSemana())
                            .horarioAbertura(entity.getHorarioAbertura())
                            .horarioFechamento(entity.getHorarioFechamento())
                            .build())
            .toList();
  }

  private UBS toResponse(final UBSEntity entity) {
    return UBS.builder()
            .id(entity.getId())
            .nome(entity.getNome())
            .cnpj(entity.getCnpj())
            .endereco(entity.getEndereco())
            .cidade(entity.getCidade())
            .telefone(entity.getTelefone())
            .email(entity.getEmail())
            .horariosFuncionamento(toHorarioDto(entity.getHorariosFuncionamento()))
            .build();
  }
}