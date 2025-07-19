package com.api.consultafacil.core.usecase.exception;

import static java.lang.String.format;

public class UBSJaExisteException extends BusinessException {

  private static final String ERROR_CODE = "UBS_JA_EXISTE";
  private static final String MESSAGE = "UBS com CNPJ=[%s] já existe.";

  public UBSJaExisteException(final String cnpj) {
    super(format(MESSAGE, cnpj), ERROR_CODE);
  }
}
