package com.api.consultafacil.core.dto;

import lombok.Builder;

@Builder
public record UpdateUBSDto(
        String endereco,
        String telefone,
        String email,
        String senha) {}
