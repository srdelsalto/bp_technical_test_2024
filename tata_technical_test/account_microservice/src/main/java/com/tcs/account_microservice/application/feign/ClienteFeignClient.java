package com.tcs.account_microservice.application.feign;

import com.tcs.account_microservice.application.dtos.ClienteDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "cliente-service", url = "${cliente.service.url}")
public interface ClienteFeignClient {
    @GetMapping("/{clientId}")
    ClienteDTO getCliente(@PathVariable("clientId") Long clientId);
}
