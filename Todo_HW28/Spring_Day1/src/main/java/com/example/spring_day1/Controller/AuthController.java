package com.example.spring_day1.Controller;

import com.example.spring_day1.Model.MyUser;
import com.example.spring_day1.Service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @GetMapping("/get")
    public ResponseEntity getUsers(){
        List<MyUser> myUserList = authService.getUsers();
        return ResponseEntity.status(200).body(myUserList);
    }

    @PostMapping("/register")
    public ResponseEntity registerUser(@Valid @RequestBody MyUser myUser){
        authService.registerUser(myUser);
        return ResponseEntity.status(200).body("User Registered!!");
    }

    @PostMapping("/admin")
    public ResponseEntity admin(){
        return ResponseEntity.status(200).body("Welcome Admin");
    }

    @PostMapping("/user")
    public ResponseEntity user(){
        return ResponseEntity.status(200).body("Welcome User");
    }

    @PostMapping("/login")
    public ResponseEntity login(){
        return ResponseEntity.status(200).body("Login");
    }

    @PostMapping("/logout")
    public ResponseEntity logout(){
        return ResponseEntity.status(200).body("Logout");
    }





}
