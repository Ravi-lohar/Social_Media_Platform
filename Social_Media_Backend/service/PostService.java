package com.example.Social_Media_Backend.service;

import com.example.Social_Media_Backend.model.Post;
import com.example.Social_Media_Backend.repository.IPostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PostService {

    @Autowired
    IPostRepo postRepo ;


    public ResponseEntity<String> createPost(Post post) {
        try {
            post.setPostCreatedTimeStamp(LocalDateTime.now());

            postRepo.save(post);
            return new ResponseEntity<>("Post Created Successfully" , HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage() , HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public boolean validatePost(Post instaPost) {
        return postRepo.existsById(instaPost.getPostId()) && instaPost!=null;
    }

    public ResponseEntity<List<Post>> getAllPost() {
        try {
            return new ResponseEntity<>(postRepo.findAll() , HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
