package com.api.consultafacil.core.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalTime;
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
public class HorarioFuncionamentoDto {

  private Integer id;

  private String diaSemana;

  @JsonFormat(pattern = "HH:mm")
  private LocalTime horarioAbertura;

  @JsonFormat(pattern = "HH:mm")
  private LocalTime horarioFechamento;
}
