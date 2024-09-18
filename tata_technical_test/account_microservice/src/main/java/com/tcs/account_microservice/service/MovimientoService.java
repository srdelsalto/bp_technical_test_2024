package com.tcs.account_microservice.service;

import com.tcs.account_microservice.exception.SaldoNoDisponibleException;
import com.tcs.account_microservice.model.Cuenta;
import com.tcs.account_microservice.model.Movimiento;
import com.tcs.account_microservice.repository.CuentaRepository;
import com.tcs.account_microservice.repository.MovimientoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovimientoService {

    @Autowired
    private MovimientoRepository movimientoRepository;

    @Autowired
    private CuentaRepository cuentaRepository;

    public Movimiento registrarMovimiento(Movimiento movimiento) {
        Cuenta cuenta = cuentaRepository.findById(movimiento.getCuenta().getNumeroCuenta())
                .orElseThrow(() -> new RuntimeException("Cuenta no encontrada"));

        double nuevoSaldo = cuenta.getSaldoInicial() + movimiento.getValor();

        if (nuevoSaldo < 0) {
            throw new SaldoNoDisponibleException("Saldo no disponible");
        }

        cuenta.setSaldoInicial(nuevoSaldo);
        cuentaRepository.save(cuenta);
        return movimientoRepository.save(movimiento);
    }

    public List<Movimiento> listarMovimientosPorCuenta(String numeroCuenta) {
        return movimientoRepository.findByCuentaNumeroCuenta(numeroCuenta);
    }
}
