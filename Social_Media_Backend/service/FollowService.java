package com.example.Social_Media_Backend.service;

import com.example.Social_Media_Backend.model.Follow;
import com.example.Social_Media_Backend.model.User;
import com.example.Social_Media_Backend.repository.IFollowRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.swing.text.html.HTML;

@Service
public class FollowService {

    @Autowired
    IFollowRepo followRepo ;


    public ResponseEntity<String> follow(Follow follow, User followerData) {
        try {
            follow.setFollower(followerData);
            followRepo.save(follow);
            return new ResponseEntity<>(followerData.getUserName() + " is now following " , HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage() , HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
