package com.tcs.account_microservice.repository;

import com.tcs.account_microservice.model.Cuenta;
import com.tcs.account_microservice.model.Movimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface MovimientoRepository extends JpaRepository<Movimiento, Long> {
    List<Movimiento> findByCuentaNumeroCuenta(String numeroCuenta);
    List<Movimiento> findByCuenta_Id(Long cuentaId);
    List<Movimiento> findByCuentaAndFechaBetween(Cuenta cuenta, LocalDate fechaInicio, LocalDate fechaFin);
}