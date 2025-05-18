package com.fahmi.ecommerce.model.dto.request;

import lombok.Getter;

@Getter
public class UserRegistrationRequest {
    private String fullName;
    private String email;
    private String username;
    private String password;
}
