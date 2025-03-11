package com.franklin.techblog.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.franklin.techblog.model.User;

/**
 * Represents  a blog post
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class BlogPost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long blogpost_id;

    private String title;
    private String content;
    private String pictureURL;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User author;
}
