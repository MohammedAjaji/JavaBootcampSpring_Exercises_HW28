package com.example.spring_homework28.Controller;

import com.example.spring_homework28.Model.MyUser;
import com.example.spring_homework28.Repository.MyUserRepository;
import com.example.spring_homework28.Service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity registerUser(@Valid @RequestBody MyUser myUser){
        authService.registerUser(myUser);
        return ResponseEntity.status(200).body("User Registered!!");
    }

    @PostMapping("/admin")
    public ResponseEntity admin(){
        return ResponseEntity.status(200).body("Welcome Admin");
    }

    @PostMapping("/customer")
    public ResponseEntity customer(){
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
