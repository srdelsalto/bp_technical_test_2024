package com.tcs.client_microservice.service;

import com.tcs.client_microservice.application.dto.request.CreateClientUserRequest;
import com.tcs.client_microservice.exception.ClienteNotFoundException;
import com.tcs.client_microservice.model.Client;
import com.tcs.client_microservice.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;

    public List<Client> listarTodos() {
        return clienteRepository.findAll();
    }

    public Client buscarPorId(Long id) {
        return clienteRepository.findById(id).get();
    }

    public Client crear(CreateClientUserRequest client) {
        Client clientModel = new Client();

        clientModel.setName(client.getName());
        clientModel.setGender(client.getGender());
        clientModel.setAge(client.getAge());
        clientModel.setIdentification(client.getIdentification());
        clientModel.setAddress(client.getAddress());
        clientModel.setPhone(client.getPhone());
        clientModel.setPassword(client.getPassword());
        clientModel.setStatus(client.getStatus());

        return clienteRepository.save(clientModel);
    }

    public Client actualizar(Long id, Client clientActualizado) {
        Client client = clienteRepository.findById(id)
                .orElseThrow(() -> new ClienteNotFoundException("Cliente no encontrado con id: " + id));


        client.setName(clientActualizado.getName());
        client.setPassword(clientActualizado.getPassword());
        client.setStatus(clientActualizado.isStatus());
        return clienteRepository.save(client);
    }

    public void eliminar(Long id) {
        clienteRepository.deleteById(id);
    }
}
