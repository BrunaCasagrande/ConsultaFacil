package com.api.consultafacil.presenter;

import com.api.consultafacil.core.domain.HorarioFuncionamento;
import com.api.consultafacil.presenter.response.HorarioFuncionamentoPresenterResponse;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class HorarioFuncionamentoPresenter {

  public List<HorarioFuncionamentoPresenterResponse> parseToResponse(final List<HorarioFuncionamento> horarioFuncionamentos) {
    return horarioFuncionamentos.stream()
            .map(horario ->
                    HorarioFuncionamentoPresenterResponse.builder()
                            .id(horario.getId())
                            .diaSemana(horario.getDiaSemana())
                            .horarioAbertura(horario.getHoraAbertura())
                            .horarioFechamento(horario.getHoraFechamento())
                            .build())
            .toList();
  }
}
