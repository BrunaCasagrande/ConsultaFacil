package com.api.consultafacil.presenter;

import com.api.consultafacil.core.domain.UBS;
import com.api.consultafacil.core.dto.HorarioFuncionamentoDto;
import com.api.consultafacil.presenter.response.HorarioFuncionamentoPresenterResponse;
import com.api.consultafacil.presenter.response.UBSPresenterResponse;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class UBSPresenter {

  public UBSPresenterResponse parseToResponse(final UBS ubs) {
    return UBSPresenterResponse.builder()
            .id(ubs.getId())
            .nome(ubs.getNome())
            .cnpj(ubs.getCnpj())
            .endereco(ubs.getEndereco())
            .cidade(ubs.getCidade())
            .telefone(ubs.getTelefone())
            .email(ubs.getEmail())
            .horariosFuncionamento(toHorarioPresenterResponse(ubs.getHorariosFuncionamento()))
            .build();
  }

  public List<UBSPresenterResponse> parseToResponse(final List<UBS> ubsList) {
    return ubsList.stream().map(this::parseToResponse).toList();
  }

  private List<HorarioFuncionamentoPresenterResponse> toHorarioPresenterResponse(
          final List<HorarioFuncionamentoDto> horarios) {
    return horarios.stream()
            .map(horario ->
                    HorarioFuncionamentoPresenterResponse.builder()
                            .id(horario.getId())
                            .diaSemana(horario.getDiaSemana())
                            .horarioAbertura(horario.getHorarioAbertura())
                            .horarioFechamento(horario.getHorarioFechamento())
                            .build())
            .toList();
  }
}
