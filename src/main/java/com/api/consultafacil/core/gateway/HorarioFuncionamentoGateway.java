package com.api.consultafacil.core.gateway;

import com.api.consultafacil.core.domain.HorarioFuncionamento;
import java.util.List;

public interface HorarioFuncionamentoGateway {

  List<HorarioFuncionamento> findByUbsId(final int ubsId);

  List<HorarioFuncionamento> update(final int ubsId, final List<HorarioFuncionamento> horarioFuncionamentos);
}
