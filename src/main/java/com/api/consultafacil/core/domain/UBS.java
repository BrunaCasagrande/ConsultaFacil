package com.api.consultafacil.core.domain;

import com.api.consultafacil.core.domain.exception.DomainException;
import com.api.consultafacil.core.domain.exception.ErrorDetail;
import com.api.consultafacil.core.dto.HorarioFuncionamentoDto;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
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
public class UBS {

  private Integer id;

  @NotBlank(message = "Nome da UBS é obrigatório")
  private String nome;

  @NotBlank(message = "CNPJ é obrigatório")
  @Pattern(regexp = "\\d{14}", message = "O CNPJ deve conter exatamente 14 dígitos numéricos")
  private String cnpj;

  @NotBlank(message = "Endereço é obrigatório")
  private String endereco;

  @NotBlank(message = "Cidade é obrigatória")
  private String cidade;

  @NotBlank(message = "Telefone é obrigatório")
  @Pattern(regexp = "\\+?\\d{10,15}", message = "O telefone deve conter entre 10 e 15 dígitos")
  private String telefone;

  @NotBlank(message = "E-mail é obrigatório")
  @Size(max = 255, message = "O e-mail deve ter no máximo 255 caracteres")
  @Email(message = "O e-mail deve ser válido")
  private String email;

  @NotBlank(message = "Senha é obrigatória")
  @Pattern(
          regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,16}$",
          message = "A senha deve conter entre 8 e 16 caracteres, com letras, números e pelo menos um caractere especial (@$!%*?&)")
  private String senha;

  @NotNull(message = "Horários de funcionamento são obrigatórios")
  private List<HorarioFuncionamentoDto> horariosFuncionamento;

  public static UBS createUBS(
          final String nome,
          final String cnpj,
          final String endereco,
          final String cidade,
          final String telefone,
          final String email,
          final String senha,
          final List<HorarioFuncionamentoDto> horariosFuncionamento) {

    final var ubs = UBS.builder()
            .nome(nome)
            .cnpj(cnpj)
            .endereco(endereco)
            .cidade(cidade)
            .telefone(telefone)
            .email(email)
            .senha(senha)
            .horariosFuncionamento(horariosFuncionamento)
            .build();

    validate(ubs);

    return ubs;
  }

  private static void validate(final UBS ubs) {
    final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    final Validator validator = factory.getValidator();
    final Set<ConstraintViolation<UBS>> violations = validator.validate(ubs);

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
