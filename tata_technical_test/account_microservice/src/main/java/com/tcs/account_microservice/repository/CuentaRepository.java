package com.tcs.account_microservice.repository;

import com.tcs.account_microservice.model.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CuentaRepository extends JpaRepository<Cuenta, String> {
    List<Cuenta> findByClienteId(Long clienteId);
}
