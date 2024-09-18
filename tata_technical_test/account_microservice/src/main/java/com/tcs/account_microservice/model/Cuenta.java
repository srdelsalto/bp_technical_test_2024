package com.tcs.account_microservice.model;

import com.tcs.client_microservice.model.Cliente;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Cuenta {
    @Id
    private String numeroCuenta;
    private String tipo;
    private double saldoInicial;
    private boolean estado;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;
}

