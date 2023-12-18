package com.example.Social_Media_Backend.controller;

import com.example.Social_Media_Backend.model.User;
import com.example.Social_Media_Backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;

@RestController
public class AuthController {

    @Autowired
    UserService userService ;

    @PostMapping("/create")
    public ResponseEntity<String> createUser(@RequestBody User user) throws MessagingException {
        return userService.createUser(user);
    }

    @GetMapping("activate/{userName}")
    public ResponseEntity<String> activateAccount(@PathVariable String userName){
        return userService.activate(userName);
    }

}
