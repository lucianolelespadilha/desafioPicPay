package com.llp.picpay.controller;

import com.llp.picpay.exception.PicPayExeption;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.security.InvalidParameterException;

@RestControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler(PicPayExeption.class)
    public ProblemDetail handlePicPayException(PicPayExeption e) {
        return e.toProblemDetail();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {

        var fieldErros = e.getFieldErrors().stream()
                .map(f -> new InvalidParam(f.getField(), f.getDefaultMessage()))
                .toList();

        var pb = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);

        pb.setTitle("Your request parameters didn´t validade.");
        pb.setProperty("invalid-params", fieldErros);

        return pb;
    }

    private record InvalidParam(
            String name,
            String reasom
    ) {
    }
}
