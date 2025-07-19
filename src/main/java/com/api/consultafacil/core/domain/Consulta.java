package com.api.consultafacil.core.domain;

import com.api.consultafacil.core.domain.exception.DomainException;
import com.api.consultafacil.core.domain.exception.ErrorDetail;
import com.api.consultafacil.core.dto.UBSDto;
import com.api.consultafacil.core.dto.PacienteDto;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;
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
public class Consulta {

  private Integer id;

  @NotNull(message = "Data da consulta é obrigatória")
  private LocalDate dataConsulta;

  @NotNull(message = "Hora da consulta é obrigatória")
  private LocalTime horaConsulta;

  @NotNull(message = "UBS é obrigatória")
  private UBSDto ubs;

  @NotNull(message = "Paciente é obrigatório")
  private PacienteDto paciente;

  public static Consulta createConsulta(
          final LocalDate dataConsulta,
          final LocalTime horaConsulta,
          final UBSDto ubs,
          final PacienteDto paciente) {

    final var consulta = Consulta.builder()
            .dataConsulta(dataConsulta)
            .horaConsulta(horaConsulta)
            .ubs(ubs)
            .paciente(paciente)
            .build();

    validate(consulta);

    return consulta;
  }

  private static void validate(final Consulta consulta) {
    final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    final Validator validator = factory.getValidator();
    final Set<ConstraintViolation<Consulta>> violations = validator.validate(consulta);

    if (!violations.isEmpty()) {
      final List<ErrorDetail> errors = violations.stream()
              .map(violation -> new ErrorDetail(
                      violation.getPropertyPath().toString(),
                      violation.getMessage(),
                      violation.getInvalidValue()))
              .toList();

      final String firstErrorMessage = errors.get(0).errorMessage();

      throw new DomainException(firstErrorMessage, "domain_exception", errors);
    }
  }
}
