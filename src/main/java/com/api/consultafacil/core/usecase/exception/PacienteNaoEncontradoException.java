package com.api.consultafacil.core.usecase.exception;

public class PacienteNaoEncontradoException extends BusinessException {

  private static final String MESSAGE = "Paciente com CPF=[%s] não encontrado.";
  private static final String ERROR_CODE = "PACIENTE_NAO_ENCONTRADO";

  public PacienteNaoEncontradoException(final String cpf) {
    super(String.format(MESSAGE, cpf), ERROR_CODE);
  }
}
