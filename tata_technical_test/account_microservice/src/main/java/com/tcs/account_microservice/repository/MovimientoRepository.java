package com.tcs.account_microservice.repository;

import com.tcs.account_microservice.model.Movimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovimientoRepository extends JpaRepository<Movimiento, Long> {
    List<Movimiento> findByCuentaNumeroCuenta(String numeroCuenta);
}