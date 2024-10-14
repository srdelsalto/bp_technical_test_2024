package com.tcs.account_microservice.controller;

import com.tcs.account_microservice.application.dtos.response.CuentasClientesResponse;
import com.tcs.account_microservice.model.Cuenta;
import com.tcs.account_microservice.service.CuentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cuentas")
public class CuentaController {
    @Autowired
    private CuentaService cuentaService;

    @PostMapping
    public ResponseEntity<Cuenta> crearCuenta(@RequestBody Cuenta cuenta) {
        Cuenta nuevaCuenta = cuentaService.crear(cuenta);

        return new ResponseEntity<>(nuevaCuenta, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cuenta> obtenerCuentaPorId(@PathVariable Long id) {
        Cuenta cuenta = cuentaService.obtenerCuentaPorId(id);
        return new ResponseEntity<>(cuenta, HttpStatus.OK);
    }

    @GetMapping("/numCuenta/{numCuenta}")
    public ResponseEntity<Cuenta> obtenerCuentaPorNumero(@PathVariable String numCuenta) {
        Cuenta cuenta = cuentaService.obtenerCuentaPorNumero(numCuenta);

        return new ResponseEntity<>(cuenta,HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Cuenta>> obtenerTodasLasCuentas() {
        List<Cuenta> cuentas = cuentaService.obtenerTodasLasCuentas();
        return new ResponseEntity<>(cuentas, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cuenta> actualizarCuenta(@PathVariable Long id, @RequestBody Cuenta detallesCuenta) {
        Cuenta cuentaActualizada = cuentaService.actualizarCuenta(id, detallesCuenta);
        return new ResponseEntity<>(cuentaActualizada, HttpStatus.OK);
    }

    @GetMapping("/cliente/{clienteId}")
    public CuentasClientesResponse listarCuentasPorCliente(@PathVariable Long clienteId) {
        return cuentaService.listarCuentasPorCliente(clienteId);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCuenta(@PathVariable Long id) {
        cuentaService.eliminarCuenta(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

