package com.tcs.client_microservice;

import com.tcs.client_microservice.application.dto.request.CreateClientUserRequest;
import com.tcs.client_microservice.model.Client;
import com.tcs.client_microservice.repository.ClienteRepository;
import com.tcs.client_microservice.service.ClienteService;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ClientServiceTest {

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private ClienteService clienteService;

    public ClientServiceTest() {
        MockitoAnnotations.openMocks(this); // Inicializa los mocks
    }

    @Test
    public void testCrearCliente() {
        // Crea un objeto Cliente simulado
        Client mockCliente = new Client();
        mockCliente.setName("Juan Perez");
        mockCliente.setGender("Masculino");
        mockCliente.setAge(30);
        mockCliente.setIdentification("1234567890");
        mockCliente.setAddress("Calle Falsa 123");
        mockCliente.setPhone("987654321");
        mockCliente.setPassword("password123");
        mockCliente.setStatus(true);

        // Simula el comportamiento del repositorio al guardar un cliente
        when(clienteRepository.save(any(Client.class))).thenReturn(mockCliente);

        CreateClientUserRequest createClientUserRequest = new CreateClientUserRequest();
        createClientUserRequest.setName("Juan Perez");;
        createClientUserRequest.setPhone("987654321");
        createClientUserRequest.setGender("Masculino");
        createClientUserRequest.setAge(30);
        createClientUserRequest.setIdentification("1234567890");
        createClientUserRequest.setAddress("Calle Falsa 123");
        createClientUserRequest.setPhone("987654321");
        createClientUserRequest.setPassword("password123");
        createClientUserRequest.setStatus(true);

        // Ejecuta el método a probar en el servicio
        Client creado = clienteService.crear(createClientUserRequest);

        // Verifica que el cliente no sea null
        assertNotNull(creado);
    }
}
