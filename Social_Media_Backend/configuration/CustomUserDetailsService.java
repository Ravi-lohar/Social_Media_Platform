package com.example.Social_Media_Backend.configuration;

import com.example.Social_Media_Backend.model.User;
import com.example.Social_Media_Backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UserService userService ;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
         User user =  userService.findByUserName(username);
        if(user == null){
            throw new UsernameNotFoundException("Username not found");
        }
        else{
            return new CustomUserDetails(user);
        }
    }
}
