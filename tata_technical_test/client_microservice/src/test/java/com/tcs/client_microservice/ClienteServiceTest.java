package com.tcs.client_microservice;

import com.tcs.client_microservice.model.Cliente;
import com.tcs.client_microservice.service.ClienteService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class ClienteServiceTest {

    @Autowired
    private ClienteService clienteService;

    @Test
    public void testCrearCliente() {
        Cliente cliente = new Cliente();
        cliente.setNombre("Juan Perez");
        cliente.setContrasena("password123");
        cliente.setEstado(true);

        Cliente creado = clienteService.crear(cliente);
        assertNotNull(creado.getId());
        assertEquals("Juan Perez", creado.getNombre());
    }
}
