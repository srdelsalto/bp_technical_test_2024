package com.tcs.account_microservice.application.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ReporteDTO {
    private String numeroCuenta;
    private String tipoCuenta;
    private double saldoInicial;
    private Boolean estadoCuenta;
    private List<MovimientoDTO> movimientos;
}
