package com.api.consultafacil.infra.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "paciente")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PacienteEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(name = "nome", nullable = false)
  private String nome;

  @Column(name = "cpf", nullable = false, unique = true)
  private String cpf;

  @Column(name = "cartao_sus", nullable = false, unique = true)
  private String cartaoSus;

  @Column(name = "telefone", nullable = false, unique = true)
  private String telefone;

  @Column(name = "email", nullable = false, unique = true)
  private String email;

  @Column(name = "senha", nullable = false)
  private String senha;
}
