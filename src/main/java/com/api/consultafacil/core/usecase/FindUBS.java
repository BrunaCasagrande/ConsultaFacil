package com.api.consultafacil.core.usecase;

import com.api.consultafacil.core.domain.UBS;
import com.api.consultafacil.core.gateway.UBSGateway;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FindUBS {

  private final UBSGateway ubsGateway;

  public Optional<UBS> execute(final String cnpj) {
    return ubsGateway.findByCnpj(cnpj);
  }
}
