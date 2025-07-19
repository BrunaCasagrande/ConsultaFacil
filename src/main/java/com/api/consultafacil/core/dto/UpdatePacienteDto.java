package com.api.consultafacil.core.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdatePacienteDto {

  private String nome;

  private String telefone;

  private String email;

  private String senha;

  private String cartaoSus;
}
