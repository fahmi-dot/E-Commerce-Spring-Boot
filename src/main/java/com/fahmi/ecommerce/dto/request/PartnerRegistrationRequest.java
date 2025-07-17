package com.fahmi.ecommerce.dto.request;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PartnerRegistrationRequest {
    private String idCardNumber;
    private String bankName;
    private String bankAccountName;
    private String bankAccountNumber;
    private String address;
}
