package com.fahmi.ecommerce.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserRegistrationResponse {
    private String userId;
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String phoneNumber;
}
