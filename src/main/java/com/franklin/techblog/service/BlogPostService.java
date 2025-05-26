package com.franklin.techblog.service;

import com.franklin.techblog.dto.BlogPostFullDto;
import com.franklin.techblog.dto.CreateBlogPostDto;
import com.franklin.techblog.entity.BlogUser;
import com.franklin.techblog.model.BlogPost;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface BlogPostService {

    List<BlogPost> getRecentPosts(Pageable pageable);

    Optional<BlogPost> getPostById(Long id);

    void createBlogPost(CreateBlogPostDto newPost, BlogUser user);
}
