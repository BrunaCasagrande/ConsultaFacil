package com.api.consultafacil.infra.persistence.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import lombok.*;

@Entity
@Table(name = "consulta")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ConsultaEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(name = "data_consulta", nullable = false)
  private LocalDate dataConsulta;

  @Column(name = "hora_consulta", nullable = false)
  private LocalTime horaConsulta;

  @ManyToOne
  @JoinColumn(name = "ubs_id", nullable = false)
  private UBSEntity ubs;

  @ManyToOne
  @JoinColumn(name = "paciente_id", nullable = false)
  private PacienteEntity paciente;
}
