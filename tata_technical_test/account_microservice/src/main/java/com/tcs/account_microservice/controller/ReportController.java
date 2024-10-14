package com.tcs.account_microservice.controller;

import com.tcs.account_microservice.application.dtos.ReporteDTO;
import com.tcs.account_microservice.application.dtos.ReporteTotalDTO;
import com.tcs.account_microservice.service.ReporteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/reportes")
public class ReportController {
    @Autowired
    private ReporteService reporteService;

    @GetMapping
    public ResponseEntity<ReporteTotalDTO> obtenerReporte(
            @RequestParam Long clienteId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaInicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaFin) {

        ReporteTotalDTO reporte = reporteService.generarReporte(clienteId, fechaInicio, fechaFin);
        return new ResponseEntity<>(reporte, HttpStatus.OK);
    }
}
