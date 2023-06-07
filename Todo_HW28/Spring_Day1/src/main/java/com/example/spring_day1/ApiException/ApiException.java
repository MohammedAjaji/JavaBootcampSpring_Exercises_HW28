package com.example.spring_day1.ApiException;

import com.example.spring_day1.ApiResponce.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class ApiException extends RuntimeException{

    public ApiException(String message){
        super(message);
    }

}
