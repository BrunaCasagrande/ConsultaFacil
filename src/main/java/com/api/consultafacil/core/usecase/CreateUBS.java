package com.api.consultafacil.core.usecase;

import com.api.consultafacil.core.domain.UBS;
import com.api.consultafacil.core.gateway.UBSGateway;
import com.api.consultafacil.core.usecase.exception.UBSJaExisteException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreateUBS {

  private final UBSGateway ubsGateway;

  public UBS execute(final UBS request) {
    final var ubs = ubsGateway.findByCnpj(request.getCnpj());

    if (ubs.isPresent()) {
      throw new UBSJaExisteException(request.getCnpj());
    }

    final var novaUbs = UBS.createUBS(
            request.getNome(),
            request.getCnpj(),
            request.getEndereco(),
            request.getCidade(),
            request.getTelefone(),
            request.getEmail(),
            request.getSenha(),
            request.getHorariosFuncionamento()
    );

    return ubsGateway.save(novaUbs);
  }
}
