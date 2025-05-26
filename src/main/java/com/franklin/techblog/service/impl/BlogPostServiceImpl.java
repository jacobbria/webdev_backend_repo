package com.franklin.techblog.service.impl;

import com.franklin.techblog.dto.BlogPostFullDto;
import com.franklin.techblog.dto.BlogPostSummaryDto;
import com.franklin.techblog.dto.CreateBlogPostDto;
import com.franklin.techblog.entity.BlogUser;
import com.franklin.techblog.mapper.BlogPostMapper;
import com.franklin.techblog.model.BlogPost;
import com.franklin.techblog.repository.BlogPostRepository;
import com.franklin.techblog.service.BlogPostService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BlogPostServiceImpl implements BlogPostService {
    private final BlogPostRepository blogPostRepository;

    @Override
    public List<BlogPost> getRecentPosts(Pageable pageable) {
        return blogPostRepository.findRecentPosts(pageable);
    }

    @Override
    public Optional<BlogPost> getPostById(Long id) { return blogPostRepository.findById(id); }

    @Override
    public void createBlogPost(CreateBlogPostDto newPost, BlogUser author) {
        BlogPost blogPost = BlogPostMapper.mapToBlogPost(newPost, author);
    }
}
