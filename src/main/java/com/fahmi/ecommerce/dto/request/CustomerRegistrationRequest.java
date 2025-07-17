package com.fahmi.ecommerce.dto.request;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerRegistrationRequest {
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String password;
    private String phoneNumber;
}
