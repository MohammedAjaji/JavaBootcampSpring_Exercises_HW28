package com.example.spring_day1.Advice;

import com.example.spring_day1.ApiException.ApiException;
import com.example.spring_day1.ApiResponce.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class Advice {

    @ExceptionHandler(ApiException.class)
    public ResponseEntity ApiException (ApiException e){
        String message = e.getMessage();
        return ResponseEntity.status(400).body(new ApiResponse(message));
    }
}
