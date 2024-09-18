package com.tcs.account_microservice.controller;

import com.tcs.account_microservice.model.Cuenta;
import com.tcs.account_microservice.service.CuentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cuentas")
public class CuentaController {
    @Autowired
    private CuentaService cuentaService;

    @PostMapping
    public Cuenta crearCuenta(@RequestBody Cuenta cuenta) {
        return cuentaService.crear(cuenta);
    }

    @GetMapping("/{clienteId}")
    public List<Cuenta> listarCuentasPorCliente(@PathVariable Long clienteId) {
        return cuentaService.listarCuentasPorCliente(clienteId);
    }
}

