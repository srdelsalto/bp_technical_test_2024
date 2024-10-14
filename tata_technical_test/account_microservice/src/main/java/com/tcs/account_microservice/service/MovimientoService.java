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

    public Movimiento registrarMovimiento(Long cuentaId, Movimiento movimiento) {
        Cuenta cuenta = cuentaRepository.findById(cuentaId)
                .orElseThrow(() -> new RuntimeException("Cuenta no encontrada"));

        double nuevoSaldo = cuenta.getSaldoInicial() + movimiento.getValor();

        if (nuevoSaldo < 0) {
            throw new SaldoNoDisponibleException("Saldo no disponible");
        }

        movimiento.setCuenta(cuenta);
        movimiento.setSaldo(nuevoSaldo);
        cuenta.setSaldoInicial(nuevoSaldo);
        cuentaRepository.save(cuenta);
        return movimientoRepository.save(movimiento);
    }

    public List<Movimiento> listarMovimientosPorCuenta(Long cuentaId) {
        return movimientoRepository.findByCuenta_Id(cuentaId);
    }

    public Movimiento actualizarMovimiento(Movimiento detallesMovimiento) {
        Long id = detallesMovimiento.getId();

        Movimiento movimiento = movimientoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Movimiento no encontrado"));

        double saldoOriginal = movimiento.getSaldo();
        double nuevoSaldo = saldoOriginal - movimiento.getValor() + detallesMovimiento.getValor();

        // Validación de saldo
        if (nuevoSaldo < 0) {
            throw new RuntimeException("Saldo no disponible para esta actualización");
        }

        movimiento.setTipoMovimiento(detallesMovimiento.getTipoMovimiento());
        movimiento.setValor(detallesMovimiento.getValor());
        movimiento.setSaldo(nuevoSaldo);
        movimiento.setFecha(detallesMovimiento.getFecha());

        // Actualiza el saldo en la cuenta
        Cuenta cuenta = movimiento.getCuenta();
        cuenta.setSaldoInicial(nuevoSaldo);
        cuentaRepository.save(cuenta);  // Usando cuentaRepository para guardar

        return movimientoRepository.save(movimiento);
    }

    public void eliminarMovimiento(Long id) {
        movimientoRepository.deleteById(id);
    }
}
