package com.fahmi.ecommerce.controller;

import com.fahmi.ecommerce.constant.Endpoint;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = Endpoint.SELLER)
public class SellerController {

    @GetMapping(path = "/hello")
    public String hello() {
        return "Hello Seller";
    }

}
