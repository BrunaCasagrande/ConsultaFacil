package com.api.consultafacil.presenter;

import com.api.consultafacil.core.domain.Paciente;
import com.api.consultafacil.presenter.response.PacientePresenterResponse;
import org.springframework.stereotype.Component;

@Component
public class PacientePresenter {

  public PacientePresenterResponse parseToResponse(final Paciente paciente) {
    return PacientePresenterResponse.builder()
            .id(paciente.getId())
            .nome(paciente.getNome())
            .cpf(paciente.getCpf())
            .cartaoSus(paciente.getCartaoSus())
            .telefone(paciente.getTelefone())
            .email(paciente.getEmail())
            .build();
  }
}
