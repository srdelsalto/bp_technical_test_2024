package com.tcs.account_microservice.service;

import com.tcs.account_microservice.model.Cuenta;
import com.tcs.account_microservice.repository.CuentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CuentaService {

    @Autowired
    private CuentaRepository cuentaRepository;

    public Cuenta crear(Cuenta cuenta) {
        return cuentaRepository.save(cuenta);
    }

    public List<Cuenta> listarCuentasPorCliente(Long clienteId) {
        return cuentaRepository.findByClienteId(clienteId);
    }
}