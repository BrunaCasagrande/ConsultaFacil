package com.api.consultafacil.presenter.response;

import java.time.LocalDate;
import java.time.LocalTime;
import lombok.Builder;

@Builder
public record ConsultaPresenterResponse(
        Integer id,
        LocalDate dataConsulta,
        LocalTime horaConsulta,
        Integer pacienteId,
        Integer ubsId
) {}
