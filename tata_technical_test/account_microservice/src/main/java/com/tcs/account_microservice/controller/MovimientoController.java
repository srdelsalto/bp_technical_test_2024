package com.tcs.account_microservice.controller;

import com.tcs.account_microservice.model.Movimiento;
import com.tcs.account_microservice.service.MovimientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movimientos")
public class MovimientoController {

    @Autowired
    private MovimientoService movimientoService;

    @PostMapping("/cuenta/{cuentaId}")
    public ResponseEntity<Movimiento> registrarMovimiento(@PathVariable Long cuentaId, @RequestBody Movimiento movimiento) {
        Movimiento nuevoMovimiento = movimientoService.registrarMovimiento(cuentaId, movimiento);
        return new ResponseEntity<>(nuevoMovimiento, HttpStatus.CREATED);
    }

    @GetMapping("/cuenta/{cuentaId}")
    public ResponseEntity<List<Movimiento>> obtenerMovimientosPorCuenta(@PathVariable Long cuentaId) {
        List<Movimiento> movimientos = movimientoService.listarMovimientosPorCuenta(cuentaId);
        return new ResponseEntity<>(movimientos, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarMovimiento(@PathVariable Long id) {
        movimientoService.eliminarMovimiento(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping
    public ResponseEntity<Movimiento> actualizarMovimiento(@RequestBody Movimiento detallesMovimiento) {
        Movimiento movimientoActualizado = movimientoService.actualizarMovimiento(detallesMovimiento);
        return new ResponseEntity<>(movimientoActualizado, HttpStatus.OK);
    }

}

