package com.fahmi.ecommerce.controller;

import com.fahmi.ecommerce.constant.Endpoint;
import com.fahmi.ecommerce.dto.request.UserLoginRequest;
import com.fahmi.ecommerce.dto.request.CustomerRegistrationRequest;
import com.fahmi.ecommerce.dto.response.UserLoginResponse;
import com.fahmi.ecommerce.dto.response.UserRegistrationResponse;
import com.fahmi.ecommerce.service.UserService;
import com.fahmi.ecommerce.util.ResponseUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(Endpoint.AUTH)
public class AuthController {
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> customerRegistration(@Valid @RequestBody CustomerRegistrationRequest request) {
        UserRegistrationResponse response = userService.customerRegistration(request);
        return ResponseUtil.response(
                HttpStatus.CREATED,
                "User registered as customer successfully",
                response
        );
    }

    @PostMapping("/login")
    public ResponseEntity<?> userLogin(@Valid @RequestBody UserLoginRequest request) {
        UserLoginResponse response = userService.userLogin(request);
        return ResponseUtil.response(
                HttpStatus.OK,
                "Login successfully",
                response
        );
    }
}
