package com.api.consultafacil.core.gateway;

import com.api.consultafacil.core.domain.UBS;
import java.util.List;
import java.util.Optional;

public interface UBSGateway {

  UBS save(final UBS ubs);

  Optional<UBS> findByCnpj(final String cnpj);

  UBS update(final UBS ubs);

  void deleteByCnpj(final String cnpj);
}
