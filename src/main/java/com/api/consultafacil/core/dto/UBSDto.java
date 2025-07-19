package com.api.consultafacil.core.dto;

import java.util.List;
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
public class UBSDto {

  private Integer id;

  private String nome;

  private String cnpj;

  private String endereco;

  private String cidade;

  private String telefone;

  private String email;

  private List<HorarioFuncionamentoDto> horariosFuncionamento;
}
