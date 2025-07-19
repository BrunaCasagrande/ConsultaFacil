package com.api.consultafacil.infra.gateway;

import static java.lang.String.format;

import com.api.consultafacil.core.domain.HorarioFuncionamento;
import com.api.consultafacil.core.dto.UBSDto;
import com.api.consultafacil.core.gateway.HorarioFuncionamentoGateway;
import com.api.consultafacil.infra.gateway.exception.GatewayException;
import com.api.consultafacil.infra.persistence.entity.HorarioFuncionamentoEntity;
import com.api.consultafacil.infra.persistence.entity.UBSEntity;
import com.api.consultafacil.infra.persistence.repository.HorarioFuncionamentoRepository;
import com.api.consultafacil.infra.persistence.repository.UBSRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class HorarioFuncionamentoGatewayImpl implements HorarioFuncionamentoGateway {

  private final HorarioFuncionamentoRepository horarioFuncionamentoRepository;
  private final UBSRepository ubsRepository;

  @Override
  public List<HorarioFuncionamento> findByUbsId(final int ubsId) {
    final var entities = horarioFuncionamentoRepository.findByUbsId(ubsId);

    return entities.stream()
            .map(entity -> {
              final var domain = HorarioFuncionamento.createHorarioFuncionamento(
                      entity.getDiaSemana(),
                      entity.getHorarioAbertura(),
                      entity.getHorarioFechamento(),
                      toUbsDto(entity.getUbs())
              );
              domain.setId(entity.getId());
              return domain;
            })
            .toList();
  }

  @Override
  public List<HorarioFuncionamento> update(final int ubsId, final List<HorarioFuncionamento> horarioFuncionamentos) {
    final UBSEntity ubs = ubsRepository.findById(ubsId)
            .orElseThrow(() -> new GatewayException(format("UBS com ID=[%s] não encontrada.", ubsId)));

    final List<HorarioFuncionamentoEntity> atualizados = new ArrayList<>();

    for (HorarioFuncionamento horario : horarioFuncionamentos) {
      Optional<HorarioFuncionamentoEntity> existente = ubs.getHorariosFuncionamento().stream()
              .filter(h -> h.getId().equals(horario.getId()))
              .findFirst();

      if (existente.isPresent()) {
        HorarioFuncionamentoEntity entity = existente.get();
        entity.setDiaSemana(horario.getDiaSemana());
        entity.setHorarioAbertura(horario.getHoraAbertura());
        entity.setHorarioFechamento(horario.getHoraFechamento());
        atualizados.add(entity);
      } else {
        HorarioFuncionamentoEntity novo = new HorarioFuncionamentoEntity();
        novo.setDiaSemana(horario.getDiaSemana());
        novo.setHorarioAbertura(horario.getHoraAbertura());
        novo.setHorarioFechamento(horario.getHoraFechamento());
        novo.setUbs(ubs);
        atualizados.add(novo);
      }
    }

    ubs.getHorariosFuncionamento().clear();
    ubs.getHorariosFuncionamento().addAll(atualizados);

    ubsRepository.save(ubs);

    return atualizados.stream()
            .map(entity -> HorarioFuncionamento.builder()
                    .id(entity.getId())
                    .diaSemana(entity.getDiaSemana())
                    .horaAbertura(entity.getHorarioAbertura())
                    .horaFechamento(entity.getHorarioFechamento())
                    .build())
            .toList();
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
            .horariosFuncionamento(null)
            .build();
  }
}
