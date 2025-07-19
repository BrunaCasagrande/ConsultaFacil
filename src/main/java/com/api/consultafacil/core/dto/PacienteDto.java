package com.api.consultafacil.core.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PacienteDto {

  private Integer id;

  private String nome;

  private String cpf;

  private String cartaoSus;

  private String telefone;

  private String email;

  @JsonIgnore
  private String senha;
}
