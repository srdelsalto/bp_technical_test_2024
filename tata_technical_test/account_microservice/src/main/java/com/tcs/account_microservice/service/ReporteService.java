package com.tcs.account_microservice.service;

import com.tcs.account_microservice.application.dtos.ClienteDTO;
import com.tcs.account_microservice.application.dtos.MovimientoDTO;
import com.tcs.account_microservice.application.dtos.ReporteDTO;
import com.tcs.account_microservice.application.dtos.ReporteTotalDTO;
import com.tcs.account_microservice.application.feign.ClienteFeignClient;
import com.tcs.account_microservice.model.Cuenta;
import com.tcs.account_microservice.model.Movimiento;
import com.tcs.account_microservice.repository.CuentaRepository;
import com.tcs.account_microservice.repository.MovimientoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReporteService {
    @Autowired
    private CuentaRepository cuentaRepository;

    @Autowired
    private MovimientoRepository movimientoRepository;

    @Autowired
    private ClienteFeignClient clienteFeignClient;

    public ReporteTotalDTO generarReporte(Long clienteId, LocalDate fechaInicio, LocalDate fechaFin) {
        //Obtener el cliente
        ClienteDTO cliente = clienteFeignClient.getCliente(clienteId);

        // Obtener las cuentas asociadas al cliente
        List<Cuenta> cuentas = cuentaRepository.findByClientId(clienteId);

        List<ReporteDTO> reporte = new ArrayList<>();

        // Iterar sobre las cuentas para agregar detalles
        for (Cuenta cuenta : cuentas) {
            ReporteDTO reporteCuenta = new ReporteDTO();
            reporteCuenta.setNumeroCuenta(cuenta.getNumeroCuenta());
            reporteCuenta.setTipoCuenta(cuenta.getTipo());
            reporteCuenta.setSaldoInicial(cuenta.getSaldoInicial());
            reporteCuenta.setEstadoCuenta(cuenta.isEstado());

            // Obtener los movimientos en el rango de fechas
            List<Movimiento> movimientos = movimientoRepository.findByCuentaAndFechaBetween(cuenta, fechaInicio, fechaFin);

            List<MovimientoDTO> movimientoDTOs = movimientos.stream().map(movimiento -> {
                MovimientoDTO dto = new MovimientoDTO();
                dto.setFecha(movimiento.getFecha().toString());
                dto.setTipoMovimiento(movimiento.getTipoMovimiento());
                dto.setValor(movimiento.getValor());
                dto.setSaldo(movimiento.getSaldo());
                return dto;
            }).collect(Collectors.toList());

            reporteCuenta.setMovimientos(movimientoDTOs);
            reporte.add(reporteCuenta);
        }

        ReporteTotalDTO totalReport = new ReporteTotalDTO();

        totalReport.setCliente(cliente);
        totalReport.setReportes(reporte);

        return totalReport;
    }
}
