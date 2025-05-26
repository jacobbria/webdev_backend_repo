package com.franklin.techblog.dto;

import com.franklin.techblog.entity.BlogUser;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;

import static com.franklin.techblog.constants.BlogPostConstants.ENTRY_LIMIT;

@Data
public class CreateBlogPostDto {
    BlogUser author;

    @NotEmpty(message = "Blog entry must have a title")
    String title;

    @Size(min = 1, max = ENTRY_LIMIT, message = "Content cannot be empty or exceed " + ENTRY_LIMIT + "characters")
    String content;
}


