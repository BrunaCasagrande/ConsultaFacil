package com.api.consultafacil.entrypoint.controller;

import com.api.consultafacil.core.dto.HorarioFuncionamentoDto;
import com.api.consultafacil.core.usecase.UpdateHorarioFuncionamento;
import com.api.consultafacil.presenter.HorarioFuncionamentoPresenter;
import com.api.consultafacil.presenter.response.HorarioFuncionamentoPresenterResponse;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/horarios-funcionamento")
public class HorarioFuncionamentoController {

  private final UpdateHorarioFuncionamento updateHorarioFuncionamento;
  private final HorarioFuncionamentoPresenter horarioFuncionamentoPresenter;

  @PutMapping("/{ubsId}")
  public ResponseEntity<List<HorarioFuncionamentoPresenterResponse>> update(
          @PathVariable final int ubsId,
          @RequestBody @Valid final List<HorarioFuncionamentoDto> request) {

    final var horariosAtualizados = updateHorarioFuncionamento.execute(ubsId, request);

    return ResponseEntity.ok(horarioFuncionamentoPresenter.parseToResponse(horariosAtualizados));
  }
}
