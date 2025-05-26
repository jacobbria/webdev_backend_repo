package com.franklin.techblog.model;

import com.franklin.techblog.entity.BlogUser;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedBy;

import java.time.LocalDateTime;

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

//    private String pictureURL;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @CreatedBy
    @ManyToOne
    @JoinColumn(name = "user_id")
    private BlogUser author;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String content;
}
