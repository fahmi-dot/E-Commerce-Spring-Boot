package com.fahmi.ecommerce.dto.request;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginRequest {
    private String usernameOrEmail;
    private String password;
}
