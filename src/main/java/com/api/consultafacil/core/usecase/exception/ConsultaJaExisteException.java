package com.api.consultafacil.core.usecase.exception;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

public class ConsultaJaExisteException extends BusinessException {

  private static final String MESSAGE =
          "Paciente com ID=[%d] já possui uma consulta em [%s] às [%s].";

  private static final String ERROR_CODE = "CONSULTA_JA_EXISTE";

  public ConsultaJaExisteException(
          final Integer pacienteId, final LocalDate data, final LocalTime hora) {
    super(String.format(MESSAGE, pacienteId, data, hora), ERROR_CODE);
  }
}
