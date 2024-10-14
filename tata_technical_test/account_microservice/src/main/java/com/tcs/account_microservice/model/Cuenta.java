package com.tcs.account_microservice.model;

import com.tcs.client_microservice.model.Client;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
public class Cuenta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String numeroCuenta;
    private String tipo;
    private Double saldoInicial;
    private boolean estado;

    //Relación con la que se guarda ClientId
    private Long clientId;
}