package com.fahmi.ecommerce.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PartnerRegistrationResponse {
    private UserRegistrationResponse user;
    private String idCardNumber;
    private String bankName;
    private String bankAccountName;
    private String bankAccountNumber;
    private String address;
}
