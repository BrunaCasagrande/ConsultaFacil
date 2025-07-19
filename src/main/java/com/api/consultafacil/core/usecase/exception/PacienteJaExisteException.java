package com.api.consultafacil.core.usecase.exception;

import static java.lang.String.format;

public class PacienteJaExisteException extends BusinessException {

  private static final String ERROR_CODE = "PACIENTE_JA_EXISTE";
  private static final String MESSAGE = "Paciente com CPF=[%s] já existe.";

  public PacienteJaExisteException(final String cpf) {
    super(format(MESSAGE, cpf), ERROR_CODE);
  }
}
