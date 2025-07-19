package com.api.consultafacil.infra.persistence.repository;

import com.api.consultafacil.infra.persistence.entity.UBSEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UBSRepository extends JpaRepository<UBSEntity, Integer> {

  Optional<UBSEntity> findByCnpj(final String cnpj);

  void deleteByCnpj(final String cnpj);
}
