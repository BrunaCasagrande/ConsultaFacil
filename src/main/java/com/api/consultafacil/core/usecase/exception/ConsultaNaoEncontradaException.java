package com.api.consultafacil.core.usecase.exception;

public class ConsultaNaoEncontradaException extends BusinessException {

  private static final String MESSAGE = "Consulta com ID=[%d] não encontrada.";
  private static final String ERROR_CODE = "CONSULTA_NAO_ENCONTRADA";

  public ConsultaNaoEncontradaException(final Integer id) {
    super(String.format(MESSAGE, id), ERROR_CODE);
  }
}
