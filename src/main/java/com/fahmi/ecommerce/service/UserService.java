package com.fahmi.ecommerce.service;

import com.fahmi.ecommerce.dto.request.PartnerRegistrationRequest;
import com.fahmi.ecommerce.dto.request.UserLoginRequest;
import com.fahmi.ecommerce.dto.request.CustomerRegistrationRequest;
import com.fahmi.ecommerce.dto.response.PartnerRegistrationResponse;
import com.fahmi.ecommerce.dto.response.UserLoginResponse;
import com.fahmi.ecommerce.dto.response.UserRegistrationResponse;

public interface UserService {
    UserRegistrationResponse customerRegistration(CustomerRegistrationRequest request);

    PartnerRegistrationResponse partnerRegistration(PartnerRegistrationRequest request);

    UserLoginResponse userLogin(UserLoginRequest request);
}
