package com.tcs.account_microservice;

import com.tcs.account_microservice.model.Cuenta;
import com.tcs.account_microservice.service.CuentaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class CuentaServiceTest {

    @Autowired
    private CuentaService cuentaService;

    @Test
    public void testCrearCuenta() {
        Cuenta cuenta = new Cuenta();
        cuenta.setNumeroCuenta("123456");
        cuenta.setTipo("Ahorros");
        cuenta.setSaldoInicial(1000.0);
        cuenta.setEstado(true);
        cuenta.setClientId(Long.parseLong("7"));

        Cuenta creada = cuentaService.crear(cuenta);
        assertNotNull(creada.getNumeroCuenta());
    }
}