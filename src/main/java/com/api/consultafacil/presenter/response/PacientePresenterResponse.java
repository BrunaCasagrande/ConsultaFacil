package com.api.consultafacil.presenter.response;

import lombok.Builder;

@Builder
public record PacientePresenterResponse(
        int id,
        String nome,
        String cpf,
        String cartaoSus,
        String telefone,
        String email) {}
