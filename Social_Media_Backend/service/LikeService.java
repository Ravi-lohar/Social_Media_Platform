package com.example.Social_Media_Backend.service;

import com.example.Social_Media_Backend.model.Like;
import com.example.Social_Media_Backend.model.Post;
import com.example.Social_Media_Backend.model.User;
import com.example.Social_Media_Backend.repository.ILikeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LikeService {

    @Autowired
    ILikeRepo likeRepo ;

    public boolean isLikeAllowedOnThisPost(Post instaPost, User liker) {
        List<Like> likeList = likeRepo.findBySocialPostAndLiker(instaPost,liker);
        return likeList!=null && likeList.isEmpty();
    }

    public ResponseEntity<String> addLike(Like like) {
        try {
            likeRepo.save(like);
            return new ResponseEntity<>("Post liked Successfully" , HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(e.getMessage() , HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
