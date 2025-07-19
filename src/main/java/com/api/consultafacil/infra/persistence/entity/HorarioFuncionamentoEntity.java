package com.api.consultafacil.infra.persistence.entity;

import jakarta.persistence.*;
import java.time.LocalTime;
import lombok.*;

@Entity
@Table(name = "horario_funcionamento")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HorarioFuncionamentoEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(name = "dia_semana", nullable = false)
  private String diaSemana;

  @Column(name = "horario_abertura", nullable = false)
  private LocalTime horarioAbertura;

  @Column(name = "horario_fechamento", nullable = false)
  private LocalTime horarioFechamento;

  @ManyToOne
  @JoinColumn(name = "ubs_id", nullable = false)
  private UBSEntity ubs;
}
