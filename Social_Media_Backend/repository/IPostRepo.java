package com.example.Social_Media_Backend.repository;

import com.example.Social_Media_Backend.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPostRepo extends JpaRepository<Post , Long> {
}
