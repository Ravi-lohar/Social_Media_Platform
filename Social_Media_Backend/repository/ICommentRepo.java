package com.example.Social_Media_Backend.repository;

import com.example.Social_Media_Backend.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICommentRepo extends JpaRepository<Comment , Long> {
}
