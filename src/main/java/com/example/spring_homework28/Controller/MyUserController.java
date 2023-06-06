package com.example.spring_homework28.Controller;

import com.example.spring_homework28.Model.MyUser;
import com.example.spring_homework28.Model.Order;
import com.example.spring_homework28.Model.Product;
import com.example.spring_homework28.Service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/user")
@RequiredArgsConstructor
public class MyUserController {

    private final AuthService authService;

    @GetMapping("/get")
    public ResponseEntity getUsers(){
        List<MyUser> users = authService.getUsers();
        return ResponseEntity.status(200).body(users);
    }

    @PutMapping("/update-password")
    public ResponseEntity updateUserPassword(@AuthenticationPrincipal MyUser myUser, @RequestBody String password){
        authService.updateUserPassword(myUser, password);
        return ResponseEntity.status(200).body("Password Updated");
    }

    @PutMapping("/update-username")
    public ResponseEntity updateUserUsername(@AuthenticationPrincipal MyUser myUser, @RequestBody String username){
        authService.updateUserUsername(myUser, username);
        return ResponseEntity.status(200).body("Username Updated");
    }

    @DeleteMapping("/delete")
    public ResponseEntity deleteUser(@AuthenticationPrincipal MyUser myUser ){
        authService.deleteUser(myUser);
        return ResponseEntity.status(200).body("User Deleted");
    }

    @GetMapping("get-id/{userId}")
    public ResponseEntity getUserById(@PathVariable Integer userId){
        MyUser user = authService.getUserId(userId);
        return ResponseEntity.status(200).body(user);
    }

}