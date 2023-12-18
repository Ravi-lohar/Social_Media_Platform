package com.example.Social_Media_Backend.model;

import com.example.Social_Media_Backend.model.enums.PostType;
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
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long PostId;
    private String postContent;
    private String postCaption;
    @Enumerated(EnumType.STRING)
    private PostType postType;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY) // hide this in json but not in database table column
    private LocalDateTime postCreatedTimeStamp;

    @ManyToOne
    @JoinColumn(name = "fk_post_user_id")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private User postOwner;
}
