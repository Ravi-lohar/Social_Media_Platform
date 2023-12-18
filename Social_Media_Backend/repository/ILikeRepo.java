package com.example.Social_Media_Backend.repository;

import com.example.Social_Media_Backend.model.Like;
import com.example.Social_Media_Backend.model.Post;
import com.example.Social_Media_Backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ILikeRepo extends JpaRepository<Like, Long> {

    List<Like> findBySocialPostAndLiker(Post instaPost, User liker);
}
