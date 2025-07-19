package com.api.consultafacil.presenter;

import com.api.consultafacil.core.domain.Consulta;
import com.api.consultafacil.presenter.response.ConsultaPresenterResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ConsultaPresenter {

  public ConsultaPresenterResponse parseToResponse(final Consulta consulta) {
    return ConsultaPresenterResponse.builder()
            .id(consulta.getId())
            .dataConsulta(consulta.getDataConsulta())
            .horaConsulta(consulta.getHoraConsulta())
            .pacienteId(consulta.getPaciente().getId())
            .ubsId(consulta.getUbs().getId())
            .build();
  }
}
