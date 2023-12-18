package com.example.Social_Media_Backend.service;

import com.example.Social_Media_Backend.model.Comment;
import com.example.Social_Media_Backend.repository.ICommentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CommentService {

    @Autowired
    ICommentRepo commentRepo ;

    public ResponseEntity<String> addComment(Comment comment) {
        try {
            comment.setCommentCreationTimeStamp(LocalDateTime.now());
            commentRepo.save(comment);
            return new ResponseEntity<>("Comment added Successfully" , HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage() , HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
