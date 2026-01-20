package com.example.exception_handling_bootcamp.handler;
import com.example.exception_handling_bootcamp.entity.RootEntity;
import com.example.exception_handling_bootcamp.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = CarNotFoundException.class)
    public ResponseEntity<RootEntity<ApiError>> carHandler(CarNotFoundException carNotFoundException){
        ApiError<String>errorPage=new ApiError<>();
        errorPage.setErrorMessage(carNotFoundException.getMessage());
        errorPage.setStatus(HttpStatus.NOT_FOUND.value());
        errorPage.setCreateDate(new Date());
        return ResponseEntity.badRequest().body(RootEntity.error(errorPage,carNotFoundException.getMessage()));
    }
    @ExceptionHandler(value = BrandNotFoundException.class)
    public ResponseEntity<RootEntity<ApiError>>brandHandler(BrandNotFoundException brandNotFoundException){
        ApiError<String>errorPage=new ApiError<>();
        errorPage.setErrorMessage(brandNotFoundException.getMessage());
        errorPage.setCreateDate(new Date());
        errorPage.setStatus(HttpStatus.NOT_FOUND.value());
        return ResponseEntity.badRequest().body(RootEntity.error(errorPage,brandNotFoundException.getMessage()));
    }
    @ExceptionHandler(value = CarPriceNotValidException.class)
    public ResponseEntity<RootEntity<ApiError>>notValidPriceHandler(CarPriceNotValidException carPriceNotValidException){
        ApiError<String>errorPage=new ApiError<>();
        errorPage.setStatus(HttpStatus.BAD_REQUEST.value());
        errorPage.setErrorMessage(carPriceNotValidException.getMessage());
        errorPage.setCreateDate(new Date());
        return ResponseEntity.badRequest().body(RootEntity.error(errorPage,carPriceNotValidException.getMessage()));
    }
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
        public ResponseEntity<RootEntity<ApiError>>handlerNotValidException(MethodArgumentNotValidException validException){

        List<String> errorMessages = new ArrayList<>();

        for (FieldError error : validException.getBindingResult().getFieldErrors()) {

            errorMessages.add(error.getDefaultMessage());
        }
        String finalMessage = String.join(", ", errorMessages);
        ApiError<String> errorPage = new ApiError<>();
        errorPage.setStatus(HttpStatus.BAD_REQUEST.value());
        errorPage.setErrorMessage(finalMessage);
        errorPage.setCreateDate(new Date());
        return ResponseEntity
                .badRequest()
                .body(RootEntity.error(errorPage, finalMessage));
    }
    }


