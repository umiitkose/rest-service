package com.umiitkose.restservice.exception;


import org.springframework.http.*;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class CustomExceptionHandler  {
    @ExceptionHandler({UserNotFoundException.class})
    public ProblemDetail errorHandler() {
        return ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, "User Bilgisi bulanmadı..");
    }

}

