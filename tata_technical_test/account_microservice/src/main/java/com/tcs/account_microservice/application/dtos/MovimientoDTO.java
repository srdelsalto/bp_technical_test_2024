package com.tcs.account_microservice.application.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovimientoDTO {
    private String fecha;
    private String tipoMovimiento;
    private double valor;
    private double saldo;
}
