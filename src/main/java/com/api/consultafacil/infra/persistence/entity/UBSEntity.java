package com.api.consultafacil.infra.persistence.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import lombok.*;

@Entity
@Table(name = "ubs")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UBSEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(name = "nome", nullable = false)
  private String nome;

  @Column(name = "cnpj", nullable = false, unique = true)
  private String cnpj;

  @Column(name = "endereco", nullable = false)
  private String endereco;

  @Column(name = "cidade", nullable = false)
  private String cidade;

  @Column(name = "telefone", nullable = false, unique = true)
  private String telefone;

  @Column(name = "email", nullable = false, unique = true)
  private String email;

  @Column(name = "senha", nullable = false)
  private String senha;

  @OneToMany(
          mappedBy = "ubs",
          fetch = FetchType.EAGER,
          cascade = CascadeType.ALL,
          orphanRemoval = true)
  private List<HorarioFuncionamentoEntity> horariosFuncionamento = new ArrayList<>();
}
