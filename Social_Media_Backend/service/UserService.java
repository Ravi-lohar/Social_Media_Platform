package com.example.Social_Media_Backend.service;

import com.example.Social_Media_Backend.model.*;
import com.example.Social_Media_Backend.repository.IUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.util.List;

@Service
public class UserService {

    @Autowired
    IUserRepo userRepo ;

    @Autowired
    PostService postService ;

    @Autowired
    CommentService commentService ;

    @Autowired
    LikeService likeService ;

    @Autowired
    FollowService followService ;


    public User findByUserName(String username) {
        return userRepo.findByUserName(username);
    }

    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    public ResponseEntity<String> createUser(User user) throws MessagingException {
       User exist = userRepo.findByUserName(user.getUserName());
        if(exist == null){
            String encryptedPassword = bCryptPasswordEncoder().encode(user.getPassword());
            user.setPassword(encryptedPassword);
            user.setIsEnable(false);
            userRepo.save(user);
            EmailHandler.sendEmail(user.getEmail() , "Account Created Successfully Please Activate" , "http://localhost:8080/activate/" + user.getUserName());
            return new ResponseEntity<>("Account create Successfully with the userName Activation link sent to your mail" , HttpStatus.CREATED);
        }
        else {
            return new ResponseEntity<>("Account Already exists with the given UserName" , HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<String> createPost(Post post, String userName) {
        User postOwner = userRepo.findByUserName(userName);
        post.setPostOwner(postOwner);
        return postService.createPost(post);
    }

    public ResponseEntity<String> addComment(Comment comment, String commenterName) {
        boolean postValid = postService.validatePost(comment.getSocialPost());
        if(postValid) {
            User commenter = userRepo.findByUserName(commenterName);
            comment.setCommenter(commenter);
            return commentService.addComment(comment);
        }
        else {
            return new ResponseEntity<>("Can not comment on Invalid Post" , HttpStatus.NO_CONTENT);
        }
    }

    public ResponseEntity<String> addLike(Like like, String likerName) {

        boolean postValid = postService.validatePost(like.getSocialPost());
        if(postValid) {
            User liker = userRepo.findByUserName(likerName);
            if(likeService.isLikeAllowedOnThisPost(like.getSocialPost(),liker))
            {
                like.setLiker(liker);
                return likeService.addLike(like);
            }
            else {
                return new ResponseEntity<>("Already Liked!!" , HttpStatus.ALREADY_REPORTED);
            }
        }
        else {
            return new ResponseEntity<>("Cannot like on Invalid Post!!" , HttpStatus.NO_CONTENT);
        }
    }


    public ResponseEntity<String> updateUser(User user, String userName) {
        User user1 = userRepo.findByUserName(userName);
        if(user.getUserId().equals(user1.getUserId())){
            user1.setEmail(user.getEmail());
            user1.setAccountType(user.getAccountType());
            user1.setMobileNo(user.getMobileNo());
            user1.setProfile(user.getProfile());
            userRepo.save(user1);
            return new ResponseEntity<>("User Details updated " , HttpStatus.OK) ;
        }else {
            return new ResponseEntity<>("Invalid Operation" , HttpStatus.BAD_REQUEST) ;
        }
    }


    public ResponseEntity<List<User>> getAllUsers() {
        try {
            return new ResponseEntity<>(userRepo.findAllNormalUsers() , HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    public ResponseEntity<String> disableUserAccount(String userName) {
        User user = userRepo.findByUserName(userName);
        String roleOfUser = user.getRole();
        if(roleOfUser.equals("ROLE_USER")){
            user.setIsEnable(false);
            userRepo.save(user);
            return new ResponseEntity<>("Account Disabled Successfully" , HttpStatus.OK) ;
        }
        else{
            return new ResponseEntity<>("Unauthorised Activity" , HttpStatus.UNAUTHORIZED) ;
        }
    }

    public ResponseEntity<String> follow(Follow follow, String follower) {
        User user = follow.getFollowing();
        boolean validUser = userRepo.existsById(user.getUserId());
        User followerData = userRepo.findByUserName(follower);
        if(validUser){
            if(user.getUserId() != followerData.getUserId()){
                return followService.follow(follow , followerData);
            }
            else {
                return new ResponseEntity<>("You can't follow yourself" , HttpStatus.BAD_REQUEST);
            }
        }

        else{
            return new ResponseEntity<>("User doesn't exists " , HttpStatus.NO_CONTENT) ;
        }
    }

    public ResponseEntity<String> activate(String userName) {
        User user = userRepo.findByUserName(userName);
        if(user.getIsEnable() == true){
            return new ResponseEntity<>("Account Already Activated"  , HttpStatus.ALREADY_REPORTED);
        }
        try{
        user.setIsEnable(true);
        userRepo.save(user);
        return new ResponseEntity<>("Account Activated Successfully" , HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage() , HttpStatus.INTERNAL_SERVER_ERROR);}
    }

    public ResponseEntity<String> enableUserAccount(String userName) {
        User user = userRepo.findByUserName(userName);
        if(user.getIsEnable() == true){
            return new ResponseEntity<>("Account Already enabled"  , HttpStatus.ALREADY_REPORTED);
        }
        try{
            user.setIsEnable(true);
            userRepo.save(user);
            return new ResponseEntity<>("Account Activated Successfully" , HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage() , HttpStatus.INTERNAL_SERVER_ERROR);}
    }
}
