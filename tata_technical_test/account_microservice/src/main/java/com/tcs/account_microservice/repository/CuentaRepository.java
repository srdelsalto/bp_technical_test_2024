package com.tcs.account_microservice.repository;

import com.tcs.account_microservice.model.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CuentaRepository extends JpaRepository<Cuenta, Long> {
    List<Cuenta> findByClientId(Long clienteId);

    Optional<Cuenta> findByNumeroCuenta(String numeroCuenta);
}
