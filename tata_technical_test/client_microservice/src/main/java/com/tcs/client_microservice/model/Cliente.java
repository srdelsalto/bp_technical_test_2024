package com.tcs.client_microservice.model;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Cliente extends Persona {
    private String clienteId;
    private String contrasena;
    private boolean estado;
}