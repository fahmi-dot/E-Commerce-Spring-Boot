package com.fahmi.ecommerce.controller;

import com.fahmi.ecommerce.constant.Endpoint;
import com.fahmi.ecommerce.dto.request.CustomerRegistrationRequest;
import com.fahmi.ecommerce.dto.request.PartnerRegistrationRequest;
import com.fahmi.ecommerce.dto.response.PartnerRegistrationResponse;
import com.fahmi.ecommerce.dto.response.UserRegistrationResponse;
import com.fahmi.ecommerce.service.UserService;
import com.fahmi.ecommerce.util.ResponseUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = Endpoint.CUSTOMER)
public class CustomerController {
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> partnerRegistration(@Valid @RequestBody PartnerRegistrationRequest request) {
        PartnerRegistrationResponse response = userService.partnerRegistration(request);
        return ResponseUtil.response(
                HttpStatus.CREATED,
                "User registered as partner successfully",
                response
        );
    }
}
