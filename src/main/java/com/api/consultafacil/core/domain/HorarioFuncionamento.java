package com.api.consultafacil.core.domain;

import com.api.consultafacil.core.domain.exception.DomainException;
import com.api.consultafacil.core.domain.exception.ErrorDetail;
import com.api.consultafacil.core.dto.UBSDto;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
public class HorarioFuncionamento {

  private Integer id;

  @NotBlank(message = "Dia da semana é obrigatório")
  private String diaSemana;

  @NotNull(message = "Horário de abertura é obrigatório")
  private LocalTime horaAbertura;

  @NotNull(message = "Horário de fechamento é obrigatório")
  private LocalTime horaFechamento;

  @NotNull(message = "UBS é obrigatória")
  private UBSDto ubs;

  public static HorarioFuncionamento createHorarioFuncionamento(
          final String diaSemana,
          final LocalTime horarioAbertura,
          final LocalTime horarioFechamento,
          final UBSDto ubs) {

    final var horario = HorarioFuncionamento.builder()
            .diaSemana(diaSemana)
            .horaAbertura(horarioAbertura)
            .horaFechamento(horarioFechamento)
            .ubs(ubs)
            .build();

    validate(horario);

    return horario;
  }

  private static void validate(final HorarioFuncionamento horarioFuncionamento) {
    final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    final Validator validator = factory.getValidator();
    final Set<ConstraintViolation<HorarioFuncionamento>> violations = validator.validate(horarioFuncionamento);

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
