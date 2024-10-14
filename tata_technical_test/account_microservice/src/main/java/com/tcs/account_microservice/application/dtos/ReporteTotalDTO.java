package com.tcs.account_microservice.application.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ReporteTotalDTO {
    private ClienteDTO cliente;
    private List<ReporteDTO> reportes;
}
