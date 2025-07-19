package com.api.consultafacil.core.usecase;

import com.api.consultafacil.core.gateway.UBSGateway;
import com.api.consultafacil.core.usecase.exception.UBSNaoEncontradaException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class DeleteUBS {

  private final UBSGateway ubsGateway;

  @Transactional
  public void execute(final String cnpj) {
    ubsGateway.findByCnpj(cnpj)
            .orElseThrow(() -> new UBSNaoEncontradaException(cnpj));

    ubsGateway.deleteByCnpj(cnpj);
  }
}
