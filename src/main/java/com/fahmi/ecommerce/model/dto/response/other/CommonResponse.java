package com.fahmi.ecommerce.model.dto.response.other;

import lombok.Data;

@Data
public class CommonResponse<T> {
    private String message;
    private int statusCode;
    private T data;
}
