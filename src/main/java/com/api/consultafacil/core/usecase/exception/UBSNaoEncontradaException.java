package com.api.consultafacil.core.usecase.exception;

public class UBSNaoEncontradaException extends BusinessException {

  private static final String MESSAGE = "UBS com identificador [%s] não encontrada.";
  private static final String ERROR_CODE = "UBS_NAO_ENCONTRADA";

  public UBSNaoEncontradaException(final String identificador) {
    super(String.format(MESSAGE, identificador), ERROR_CODE);
  }
}
