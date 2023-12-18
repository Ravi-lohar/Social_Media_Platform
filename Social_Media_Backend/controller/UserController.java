package com.example.Social_Media_Backend.controller;

import com.example.Social_Media_Backend.model.*;
import com.example.Social_Media_Backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService ;

    @PostMapping("post")
    public ResponseEntity<String> createPost(@RequestBody Post post ){
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        return userService.createPost(post , userName);
    }

    @PostMapping("comment")
    public ResponseEntity<String> addComment(@RequestBody Comment comment){
        String commenterName = SecurityContextHolder.getContext().getAuthentication().getName();
        return userService.addComment(comment , commenterName);
    }

    @PostMapping("like")
    public ResponseEntity<String> addLike (@RequestBody Like like){
        String likerName = SecurityContextHolder.getContext().getAuthentication().getName();
        return userService.addLike(like , likerName);
    }

    @PostMapping("follow")
    public ResponseEntity<String> followUser(@RequestBody Follow follow){
        String follower = SecurityContextHolder.getContext().getAuthentication().getName();
        return userService.follow(follow , follower);
    }

    @PutMapping("/updateProfile")
    public ResponseEntity<String> updateUser(@RequestBody User user){
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        return userService.updateUser(user , userName);
    }

}
