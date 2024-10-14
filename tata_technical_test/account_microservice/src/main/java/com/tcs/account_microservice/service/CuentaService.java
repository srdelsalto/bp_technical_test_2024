package com.tcs.account_microservice.service;

import com.tcs.account_microservice.application.dtos.ClienteDTO;
import com.tcs.account_microservice.application.dtos.response.CuentasClientesResponse;
import com.tcs.account_microservice.application.feign.ClienteFeignClient;
import com.tcs.account_microservice.model.Cuenta;
import com.tcs.account_microservice.repository.CuentaRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CuentaService {
    @Autowired
    private ClienteFeignClient clienteFeignClient;

    @Autowired
    private CuentaRepository cuentaRepository;

    @CircuitBreaker(name = "clienteService", fallbackMethod = "clienteFallback")
    public Cuenta crear(Cuenta cuenta) {
        ClienteDTO cliente = clienteFeignClient.getCliente(cuenta.getClientId());

        if (cliente == null) {
            throw new RuntimeException("Cliente no encontrado");
        }

        // Asignar clienteId a la cuenta y guardar en la base de datos
        cuenta.setClientId(cliente.getId());

        return cuentaRepository.save(cuenta);
    }

    public Cuenta clienteFallback(Long clienteId, Throwable throwable) {
        throw new RuntimeException("No se pudo contactar al servicio de clientes. Intente más tarde.");
    }

    public Cuenta obtenerCuentaPorId(Long id) {
        return cuentaRepository.findById(id).orElseThrow(() -> new RuntimeException("Cuenta no encontrada"));
    }

    public Cuenta obtenerCuentaPorNumero(String numero) {
        return cuentaRepository.findByNumeroCuenta(numero).orElseThrow(() -> new RuntimeException("Cuenta no encontrada"));
    }

    public List<Cuenta> obtenerTodasLasCuentas() {
        return cuentaRepository.findAll();
    }

    public Cuenta actualizarCuenta(Long id, Cuenta detallesCuenta) {
        Cuenta cuenta = obtenerCuentaPorId(id);
        cuenta.setNumeroCuenta(detallesCuenta.getNumeroCuenta());
        cuenta.setTipo(detallesCuenta.getTipo());
        cuenta.setSaldoInicial(detallesCuenta.getSaldoInicial());
        cuenta.setEstado(detallesCuenta.isEstado());
        return cuentaRepository.save(cuenta);
    }

    public CuentasClientesResponse listarCuentasPorCliente(Long clienteId) {
        ClienteDTO clienteDTO = clienteFeignClient.getCliente(clienteId);
        List<Cuenta> cuentas = cuentaRepository.findByClientId(clienteId);

        CuentasClientesResponse cuentasClientesResponse = new CuentasClientesResponse();
        cuentasClientesResponse.setCliente(clienteDTO);
        cuentasClientesResponse.setCuentas(cuentas);


        return cuentasClientesResponse;
    }

    public void eliminarCuenta(Long id) {
        Cuenta cuenta = obtenerCuentaPorId(id);
        cuentaRepository.delete(cuenta);
    }
}