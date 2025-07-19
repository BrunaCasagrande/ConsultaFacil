package com.api.consultafacil.presenter.response;

import java.util.List;
import lombok.Builder;

@Builder
public record UBSPresenterResponse(
        int id,
        String nome,
        String cnpj,
        String endereco,
        String cidade,
        String telefone,
        String email,
        List<HorarioFuncionamentoPresenterResponse> horariosFuncionamento
) {}
