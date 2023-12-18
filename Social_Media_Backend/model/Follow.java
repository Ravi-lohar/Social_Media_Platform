package com.example.Social_Media_Backend.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Follow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long followId;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY) // hide this in json but not in database table column
    private LocalDateTime commentCreationTimeStamp;

    @ManyToOne
    @JoinColumn(name = "follower_id")
    User follower; // The person who wants to follow

    @ManyToOne
    @JoinColumn(name = "following")
    User following; // The person who is to whom follower is following

}
