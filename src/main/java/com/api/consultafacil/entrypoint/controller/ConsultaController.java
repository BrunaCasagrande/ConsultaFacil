package com.api.consultafacil.entrypoint.controller;

import com.api.consultafacil.core.domain.Consulta;
import com.api.consultafacil.core.dto.UpdateConsultaDto;
import com.api.consultafacil.core.usecase.CreateConsulta;
import com.api.consultafacil.core.usecase.DeleteConsulta;
import com.api.consultafacil.core.usecase.SearchConsulta;
import com.api.consultafacil.core.usecase.UpdateConsulta;
import com.api.consultafacil.presenter.ConsultaPresenter;
import com.api.consultafacil.presenter.response.ConsultaPresenterResponse;
import jakarta.validation.Valid;
import java.net.URI;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequiredArgsConstructor
@RequestMapping("/consultas")
public class ConsultaController {

  private final CreateConsulta createConsulta;
  private final DeleteConsulta deleteConsulta;
  private final SearchConsulta searchConsulta;
  private final UpdateConsulta updateConsulta;
  private final ConsultaPresenter consultaPresenter;

  @PostMapping
  public ResponseEntity<ConsultaPresenterResponse> create(
          @Valid @RequestBody final Consulta request) {

    final var consultaCriada = this.createConsulta.execute(request);

    URI location =
            ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(consultaCriada.getId())
                    .toUri();

    return ResponseEntity.created(location)
            .body(consultaPresenter.parseToResponse(consultaCriada));
  }

  @GetMapping("/{id}")
  public ResponseEntity<ConsultaPresenterResponse> findById(@PathVariable final Integer id) {
    return this.searchConsulta
            .execute(id)
            .map(consulta -> ResponseEntity.ok(consultaPresenter.parseToResponse(consulta)))
            .orElseGet(() -> ResponseEntity.notFound().build());
  }

  @PutMapping("/{id}")
  public ResponseEntity<ConsultaPresenterResponse> update(
          @PathVariable final Integer id, @Valid @RequestBody final UpdateConsultaDto request) {

    final var consultaAtualizada = this.updateConsulta.execute(id, request);

    return ResponseEntity.ok(consultaPresenter.parseToResponse(consultaAtualizada));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable final Integer id) {
    this.deleteConsulta.execute(id);
    return ResponseEntity.noContent().build();
  }
}
