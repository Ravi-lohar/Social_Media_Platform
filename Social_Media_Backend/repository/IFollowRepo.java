package com.example.Social_Media_Backend.repository;

import com.example.Social_Media_Backend.model.Follow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IFollowRepo extends JpaRepository<Follow , Long> {
}
