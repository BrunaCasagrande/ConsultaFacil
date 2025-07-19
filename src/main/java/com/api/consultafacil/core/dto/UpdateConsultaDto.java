package com.api.consultafacil.core.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateConsultaDto {

  private LocalDate dataConsulta;

  private LocalTime horaConsulta;
}
