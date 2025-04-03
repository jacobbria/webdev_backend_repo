package com.franklin.techblog.model;

import com.franklin.techblog.entity.BlogUser;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents a comment on a blog post
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long comment_id;

    private String title;
    private String content;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private BlogUser author;

    @ManyToOne
    @JoinColumn(name = "blogpost_id")
    private BlogUser blogPost;
}
