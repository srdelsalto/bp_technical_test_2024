package com.tcs.client_microservice.service;

import com.tcs.client_microservice.exception.ClienteNotFoundException;
import com.tcs.client_microservice.model.Cliente;
import com.tcs.client_microservice.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> listarTodos() {
        return clienteRepository.findAll();
    }

    public Cliente crear(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public Cliente actualizar(Long id, Cliente clienteActualizado) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new ClienteNotFoundException("Cliente no encontrado con id: " + id));
        cliente.setNombre(clienteActualizado.getNombre());
        cliente.setContrasena(clienteActualizado.getContrasena());
        cliente.setEstado(clienteActualizado.isEstado());
        return clienteRepository.save(cliente);
    }

    public void eliminar(Long id) {
        clienteRepository.deleteById(id);
    }
}
