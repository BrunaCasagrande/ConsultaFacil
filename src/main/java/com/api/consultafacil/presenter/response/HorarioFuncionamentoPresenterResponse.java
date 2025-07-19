package com.api.consultafacil.presenter.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalTime;
import lombok.Builder;

@Builder
public record HorarioFuncionamentoPresenterResponse(
        Integer id,
        String diaSemana,
        @JsonFormat(pattern = "HH:mm") LocalTime horarioAbertura,
        @JsonFormat(pattern = "HH:mm") LocalTime horarioFechamento
) {}
