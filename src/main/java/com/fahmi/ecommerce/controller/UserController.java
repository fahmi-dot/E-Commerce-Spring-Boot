package com.fahmi.ecommerce.controller;

import com.fahmi.ecommerce.constant.Endpoint;
import com.fahmi.ecommerce.model.dto.request.UserLoginRequest;
import com.fahmi.ecommerce.model.dto.request.UserRegistrationRequest;
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
@RequestMapping(path = Endpoint.AUTH)
public class UserController {
    private final UserService userService;

    @PostMapping(path = Endpoint.REGISTER)
    public ResponseEntity<?> customerRegister(@Valid @RequestBody UserRegistrationRequest userRegistrationRequest) {
        return ResponseUtil.response(
                HttpStatus.CREATED,
                "Registration success",
                userService.customerRegistration(userRegistrationRequest)
        );
    }

    @PostMapping(path = Endpoint.LOGIN)
    public ResponseEntity<?> userLogin(@Valid @RequestBody UserLoginRequest userLoginRequest) {
        return ResponseUtil.response(
                HttpStatus.OK,
                "Login success",
                userService.userLogin(userLoginRequest)
        );
    }
}
