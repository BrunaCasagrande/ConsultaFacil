package com.api.consultafacil.entrypoint.controller;

import com.api.consultafacil.core.domain.UBS;
import com.api.consultafacil.core.dto.UpdateUBSDto;
import com.api.consultafacil.core.usecase.CreateUBS;
import com.api.consultafacil.core.usecase.DeleteUBS;
import com.api.consultafacil.core.usecase.FindUBS;
import com.api.consultafacil.core.usecase.UpdateUBS;
import com.api.consultafacil.presenter.UBSPresenter;
import com.api.consultafacil.presenter.response.UBSPresenterResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import java.net.URI;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequiredArgsConstructor
@RequestMapping("/ubs")
public class UBSController {

  private final CreateUBS createUBS;
  private final FindUBS findUBS;
  private final UpdateUBS updateUBS;
  private final DeleteUBS deleteUBS;

  private final UBSPresenter ubsPresenter;

  @PostMapping
  public ResponseEntity<UBSPresenterResponse> create(@Valid @RequestBody final UBS request) {
    final var ubsCriada = this.createUBS.execute(request);

    URI location =
            ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{cnpj}")
                    .buildAndExpand(ubsCriada.getCnpj())
                    .toUri();

    return ResponseEntity.created(location).body(ubsPresenter.parseToResponse(ubsCriada));
  }

  @GetMapping("/{cnpj}")
  public ResponseEntity<UBSPresenterResponse> findByCnpj(
          @PathVariable @Pattern(regexp = "\\d{14}", message = "CNPJ deve conter 14 dígitos numéricos")
          final String cnpj) {
    return this.findUBS
            .execute(cnpj)
            .map(ubs -> ResponseEntity.ok(ubsPresenter.parseToResponse(ubs)))
            .orElseGet(() -> ResponseEntity.notFound().build());
  }

  @PutMapping("/{cnpj}")
  public ResponseEntity<UBSPresenterResponse> update(
          @PathVariable @Pattern(regexp = "\\d{14}", message = "CNPJ deve conter 14 dígitos numéricos")
          final String cnpj,
          @Valid @RequestBody final UpdateUBSDto request) {
    final var ubsAtualizada = this.updateUBS.execute(cnpj, request);

    return ResponseEntity.ok(ubsPresenter.parseToResponse(ubsAtualizada));
  }

  @DeleteMapping("/{cnpj}")
  public ResponseEntity<Void> delete(
          @PathVariable @Pattern(regexp = "\\d{14}", message = "CNPJ deve conter 14 dígitos numéricos")
          final String cnpj) {
    this.deleteUBS.execute(cnpj);
    return ResponseEntity.noContent().build();
  }
}
