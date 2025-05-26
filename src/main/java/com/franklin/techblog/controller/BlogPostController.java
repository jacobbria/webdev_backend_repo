package com.franklin.techblog.controller;

import com.franklin.techblog.constants.UserConstants;
import com.franklin.techblog.dto.*;
import com.franklin.techblog.entity.BlogUser;
import com.franklin.techblog.service.IUserService;
import com.franklin.techblog.mapper.BlogPostMapper;
import com.franklin.techblog.model.BlogPost;
import com.franklin.techblog.service.BlogPostService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.franklin.techblog.mapper.BlogPostMapper.mapToFullPostDto;

@RestController
@RequestMapping(path = "/api/blog", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class BlogPostController {

    private final BlogPostService blogPostService;
    private final IUserService userService;

    @GetMapping("/recent")
    public ResponseEntity<List<BlogPostSummaryDto>> getRecentBlogPosts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {

        Pageable pageable = PageRequest.of(page, size);
        List<BlogPost> blogPosts = blogPostService.getRecentPosts(pageable);

        // TODO: explore stream().map().toList()
        List<BlogPostSummaryDto> postList = new ArrayList<>();
        for (BlogPost post : blogPosts) {
            BlogPostSummaryDto dto = BlogPostMapper.mapToSummaryDto(post);
            postList.add(dto);
        }

        return ResponseEntity.ok(postList);
    }

    // TODO apply this error handling to other controller methods
    @GetMapping("/{id}")
    public ResponseEntity<?> getBlogPostById(@PathVariable Long id, HttpServletRequest request) {
        return blogPostService.getPostById(id)
                .<ResponseEntity<?>>map(post -> ResponseEntity.ok(mapToFullPostDto(post)))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ErrorResponseDto(
                                request.getRequestURI(),
                                HttpStatus.NOT_FOUND,
                                "Blog post with id: " + id + " not found",
                                LocalDateTime.now()
                        )));
    }

    @PostMapping
    public ResponseEntity<ResponseDto> createBlogPost(
            @Valid @RequestBody CreateBlogPostDto newPost,
            Principal principal) {

        BlogUser user = userService.getUserByEmail(principal.getName())
                .orElseThrow(() -> new RuntimeException("Authenticated user not found"));

        blogPostService.createBlogPost(newPost, user);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto("201", "New entry posted successfully"));
    }


    // TODO: add rest of CRUD functionality
}
