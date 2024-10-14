package com.tcs.account_microservice.application.dtos.response;

import com.tcs.account_microservice.application.dtos.ClienteDTO;
import com.tcs.account_microservice.model.Cuenta;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CuentasClientesResponse {
    private ClienteDTO cliente;
    private List<Cuenta> cuentas;
}
