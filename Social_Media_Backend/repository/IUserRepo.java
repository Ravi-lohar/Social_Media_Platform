package com.example.Social_Media_Backend.repository;

import com.example.Social_Media_Backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IUserRepo extends JpaRepository<User, Long> {

    User findByUserName(String username);

    @Query(value = "select * from user where role = 'ROLE_USER'" , nativeQuery = true )
    List<User> findAllNormalUsers();
}
