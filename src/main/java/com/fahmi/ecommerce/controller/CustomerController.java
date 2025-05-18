package com.fahmi.ecommerce.controller;

import com.fahmi.ecommerce.constant.Endpoint;
import com.fahmi.ecommerce.model.dto.request.SellerRegistrationRequest;
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

    @GetMapping(path = "/hello")
    public String hello() {
        return "Hello Customer";
    }

    @PutMapping(path = Endpoint.REGISTER)
    public ResponseEntity<?> sellerRegister(@Valid @RequestBody SellerRegistrationRequest sellerRegistrationRequest) {
        return ResponseUtil.response(
                HttpStatus.CREATED,
                "Registration success",
                userService.sellerRegistration(sellerRegistrationRequest)
        );
    }

}
