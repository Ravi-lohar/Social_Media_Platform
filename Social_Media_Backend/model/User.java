package com.example.Social_Media_Backend.model;

import com.example.Social_Media_Backend.model.enums.AccountType;
import com.example.Social_Media_Backend.model.enums.Gender;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId ;
    private String name ;
    @Column(unique = true)
    private String userName ;
    @Column(unique = true)
    private String email;
    private String mobileNo ;
    private String password;
    private String profile ;
    @Enumerated(EnumType.STRING)
    private Gender gender ;
    @Enumerated(EnumType.STRING)
    private AccountType accountType ;
    private Boolean IsEnable = false ;
    private String role;
}
