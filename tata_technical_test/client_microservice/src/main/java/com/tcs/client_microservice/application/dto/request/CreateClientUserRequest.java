package com.tcs.client_microservice.application.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateClientUserRequest {
    private String name;
    private String gender;
    private int age;
    private String identification;
    private String address;
    private String phone;
    private String password;
    private Boolean status;
}
