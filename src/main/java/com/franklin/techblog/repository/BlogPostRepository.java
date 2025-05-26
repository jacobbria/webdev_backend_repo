package com.franklin.techblog.repository;

import com.franklin.techblog.dto.BlogPostSummaryDto;
import com.franklin.techblog.model.BlogPost;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BlogPostRepository extends JpaRepository<BlogPost, Long> {
    @Query("SELECT b FROM BlogPost b ORDER BY b.createdAt DESC")
    List<BlogPost> findRecentPosts(Pageable pageable);
}
