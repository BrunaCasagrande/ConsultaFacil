package com.api.consultafacil.entrypoint.controller;

import com.api.consultafacil.core.domain.Paciente;
import com.api.consultafacil.core.dto.UpdatePacienteDto;
import com.api.consultafacil.core.usecase.CreatePaciente;
import com.api.consultafacil.core.usecase.DeletePaciente;
import com.api.consultafacil.core.usecase.SearchPaciente;
import com.api.consultafacil.core.usecase.UpdatePaciente;
import com.api.consultafacil.presenter.PacientePresenter;
import com.api.consultafacil.presenter.response.PacientePresenterResponse;
import jakarta.validation.Valid;
import java.net.URI;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequiredArgsConstructor
@RequestMapping("/pacientes")
public class PacienteController {

  private final CreatePaciente createPaciente;
  private final DeletePaciente deletePaciente;
  private final SearchPaciente searchPaciente;
  private final UpdatePaciente updatePaciente;

  private final PacientePresenter pacientePresenter;

  @PostMapping
  public ResponseEntity<PacientePresenterResponse> create(@Valid @RequestBody final Paciente request) {
    final var pacienteCriado = this.createPaciente.execute(request);

    URI location =
            ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{cpf}")
                    .buildAndExpand(pacienteCriado.getCpf())
                    .toUri();

    return ResponseEntity.created(location).body(pacientePresenter.parseToResponse(pacienteCriado));
  }

  @GetMapping("/{cpf}")
  public ResponseEntity<PacientePresenterResponse> findByCpf(@PathVariable final String cpf) {
    return this.searchPaciente
            .execute(cpf)
            .map(paciente -> ResponseEntity.ok(pacientePresenter.parseToResponse(paciente)))
            .orElseGet(() -> ResponseEntity.notFound().build());
  }

  @PutMapping("/{cpf}")
  public ResponseEntity<PacientePresenterResponse> update(
          @PathVariable final String cpf, @Valid @RequestBody final UpdatePacienteDto request) {

    final var pacienteAtualizado = this.updatePaciente.execute(cpf, request);

    return ResponseEntity.ok(pacientePresenter.parseToResponse(pacienteAtualizado));
  }

  @DeleteMapping("/{cpf}")
  public ResponseEntity<Void> delete(@PathVariable final String cpf) {
    this.deletePaciente.execute(cpf);
    return ResponseEntity.noContent().build();
  }
}
