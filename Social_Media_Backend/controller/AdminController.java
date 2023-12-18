package com.example.Social_Media_Backend.controller;

import com.example.Social_Media_Backend.model.Post;
import com.example.Social_Media_Backend.model.User;
import com.example.Social_Media_Backend.service.PostService;
import com.example.Social_Media_Backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    UserService userService ;

    @Autowired
    PostService postService ;

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/posts")
    public ResponseEntity<List<Post>> getAllPost(){
        return postService.getAllPost();
    }

    @PutMapping("/disable/{userName}")
    public ResponseEntity<String> disableUserAccount(@PathVariable String userName){
        return userService.disableUserAccount(userName);
    }

    @PutMapping("/enable/{userName}")
    public ResponseEntity<String> enableUserAccount(@PathVariable String userName){
        return userService.enableUserAccount(userName);
    }




}
