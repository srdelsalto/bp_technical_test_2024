package com.tcs.account_microservice.application.dtos;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteDTO {
    private Long id;
    private String name;
    private String gender;
    private String identification;
    private String address;
    private String phone;
    private String password;
    private Boolean status;
}
