package com.tcs.client_microservice.controller;

import com.tcs.client_microservice.application.dto.request.CreateClientUserRequest;
import com.tcs.client_microservice.model.Client;
import com.tcs.client_microservice.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public List<Client> listarClientes() {
        return clienteService.listarTodos();
    }

    @GetMapping("/{id}")
    public Client buscarCliente(@PathVariable Long id) {
        return clienteService.buscarPorId(id);
    }

    @PostMapping
    public Client crearCliente(@RequestBody CreateClientUserRequest client) {
        return clienteService.crear(client);
    }

    @PutMapping("/{id}")
    public Client actualizarCliente(@PathVariable Long id, @RequestBody Client clientActualizado) {
        return clienteService.actualizar(id, clientActualizado);
    }

    @DeleteMapping("/{id}")
    public void eliminarCliente(@PathVariable Long id) {
        clienteService.eliminar(id);
    }
}
