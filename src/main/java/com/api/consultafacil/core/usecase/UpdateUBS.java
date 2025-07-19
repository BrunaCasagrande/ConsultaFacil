package com.api.consultafacil.core.usecase;

import com.api.consultafacil.core.domain.UBS;
import com.api.consultafacil.core.dto.UpdateUBSDto;
import com.api.consultafacil.core.gateway.UBSGateway;
import com.api.consultafacil.core.usecase.exception.UBSNaoEncontradaException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class UpdateUBS {

  private final UBSGateway ubsGateway;

  public UBS execute(final String cnpj, final UpdateUBSDto request) {
    final var ubs =
            ubsGateway.findByCnpj(cnpj)
                    .orElseThrow(() -> new UBSNaoEncontradaException(cnpj));

    atualizarCampos(ubs, request);

    if (request.senha() != null && !request.senha().isEmpty()) {
      final var ubsAtualizada = UBS.builder()
              .id(ubs.getId())
              .nome(ubs.getNome())
              .cnpj(ubs.getCnpj())
              .endereco(ubs.getEndereco())
              .cidade(ubs.getCidade())
              .telefone(ubs.getTelefone())
              .horariosFuncionamento(ubs.getHorariosFuncionamento())
              .email(ubs.getEmail())
              .senha(request.senha())
              .build();

      log.info("A senha da UBS foi atualizada.");
      return ubsGateway.update(ubsAtualizada);
    }

    return ubsGateway.update(ubs);
  }

  private void atualizarCampos(final UBS ubs, final UpdateUBSDto request) {
    if (request.endereco() != null) ubs.setEndereco(request.endereco());
    if (request.telefone() != null) ubs.setTelefone(request.telefone());
    if (request.email() != null) ubs.setEmail(request.email());
  }
}
