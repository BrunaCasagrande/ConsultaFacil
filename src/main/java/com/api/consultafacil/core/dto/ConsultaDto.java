package com.api.consultafacil.core.dto;

import java.time.LocalDate;
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
public class ConsultaDto {

  private Integer id;

  private LocalDate dataConsulta;

  private LocalTime horaConsulta;

  private UBSDto ubs;

  private PacienteDto paciente;
}
