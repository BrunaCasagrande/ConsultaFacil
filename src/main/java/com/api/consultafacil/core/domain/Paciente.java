package com.api.consultafacil.core.domain;

import com.api.consultafacil.core.domain.exception.DomainException;
import com.api.consultafacil.core.domain.exception.ErrorDetail;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
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
public class Paciente {

  private Integer id;

  @NotBlank(message = "Nome é obrigatório")
  private String nome;

  @NotBlank(message = "CPF é obrigatório")
  @Pattern(regexp = "\\d{11}", message = "O CPF deve conter exatamente 11 dígitos numéricos")
  private String cpf;

  @NotBlank(message = "Número do cartão SUS é obrigatório")
  @Pattern(regexp = "\\d{15}", message = "O cartão SUS deve conter exatamente 15 dígitos")
  private String cartaoSus;

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

  public static Paciente createPaciente(
          final String nome,
          final String cpf,
          final String cartaoSus,
          final String telefone,
          final String email,
          final String senha) {

    final var paciente = Paciente.builder()
            .nome(nome)
            .cpf(cpf)
            .cartaoSus(cartaoSus)
            .telefone(telefone)
            .email(email)
            .senha(senha)
            .build();

    validate(paciente);
    return paciente;
  }

  private static void validate(final Paciente paciente) {
    final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    final Validator validator = factory.getValidator();
    final Set<ConstraintViolation<Paciente>> violations = validator.validate(paciente);

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
