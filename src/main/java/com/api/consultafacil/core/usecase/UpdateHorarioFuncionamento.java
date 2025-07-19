package com.api.consultafacil.core.usecase;

import com.api.consultafacil.core.domain.HorarioFuncionamento;
import com.api.consultafacil.core.dto.HorarioFuncionamentoDto;
import com.api.consultafacil.core.gateway.HorarioFuncionamentoGateway;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UpdateHorarioFuncionamento {

  private final HorarioFuncionamentoGateway horarioFuncionamentoGateway;

  public List<HorarioFuncionamento> execute(final int ubsId, final List<HorarioFuncionamentoDto> request) {
    final var horariosExistentes = horarioFuncionamentoGateway.findByUbsId(ubsId);

    if (horariosExistentes.isEmpty()) {
      return horarioFuncionamentoGateway.update(ubsId, toHorarioList(request));
    }

    final List<HorarioFuncionamento> horariosAtualizados = new ArrayList<>();

    for (HorarioFuncionamentoDto horario : request) {
      final Optional<HorarioFuncionamento> existente =
              horariosExistentes.stream().filter(h -> h.getId().equals(horario.getId())).findFirst();

      if (existente.isPresent()) {
        HorarioFuncionamento atualizado = existente.get();
        atualizado.setDiaSemana(horario.getDiaSemana());
        atualizado.setHoraAbertura(horario.getHorarioAbertura());
        atualizado.setHoraFechamento(horario.getHorarioFechamento());
        horariosAtualizados.add(atualizado);
      } else {
        horariosAtualizados.add(toHorario(horario));
      }
    }

    return horarioFuncionamentoGateway.update(ubsId, horariosAtualizados);
  }

  private List<HorarioFuncionamento> toHorarioList(final List<HorarioFuncionamentoDto> dtos) {
    return dtos.stream().map(this::toHorario).toList();
  }

  private HorarioFuncionamento toHorario(final HorarioFuncionamentoDto dto) {
    return HorarioFuncionamento.builder()
            .id(dto.getId())
            .diaSemana(dto.getDiaSemana())
            .horaAbertura(dto.getHorarioAbertura())
            .horaFechamento(dto.getHorarioFechamento())
            .build();
  }
}
