package com.fahmi.ecommerce.exception;

import com.fahmi.ecommerce.util.ResponseUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(CustomException.BadRequestException.class)
    public ResponseEntity<?> handleBadRequest(CustomException.BadRequestException ex) {
        return ResponseUtil.response(
                HttpStatus.BAD_REQUEST,
                ex.getMessage(),
                null);
    }

    @ExceptionHandler(CustomException.AuthenticationException.class)
    public ResponseEntity<?> handleAuthentication(CustomException.AuthenticationException ex) {
        return ResponseUtil.response(
                HttpStatus.UNAUTHORIZED,
                ex.getMessage(),
                null);
    }

    @ExceptionHandler(CustomException.AuthorizationException.class)
    public ResponseEntity<?> handleAuthorization(CustomException.AuthorizationException ex) {
        return ResponseUtil.response(
                HttpStatus.FORBIDDEN,
                ex.getMessage(),
                null);
    }

    @ExceptionHandler(CustomException.ResourceNotFoundException.class)
    public ResponseEntity<?> handleNotFound(CustomException.ResourceNotFoundException ex) {
        return ResponseUtil.response(
                HttpStatus.NOT_FOUND,
                ex.getMessage(),
                null);
    }

    @ExceptionHandler(CustomException.ConflictException.class)
    public ResponseEntity<?> handleConflict(CustomException.ConflictException ex) {
        return ResponseUtil.response(
                HttpStatus.CONFLICT,
                ex.getMessage(),
                null);
    }

    @ExceptionHandler(CustomException.PaymentException.class)
    public ResponseEntity<?> handlePayment(CustomException.PaymentException ex) {
        return ResponseUtil.response(
                HttpStatus.PAYMENT_REQUIRED,
                ex.getMessage(),
                null);
    }

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<?> handleMaxSizeException(MaxUploadSizeExceededException ex) {
        return ResponseUtil.response(
                HttpStatus.EXPECTATION_FAILED,
                "File size exceeds 5MB limit.",
                null);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleAll(Exception ex) {
        ex.printStackTrace();

        return ResponseUtil.response(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "An unexpected internal server error occurred.",
                null);
    }
}