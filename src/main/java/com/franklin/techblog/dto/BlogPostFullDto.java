package com.franklin.techblog.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

import static com.franklin.techblog.constants.BlogPostConstants.ENTRY_LIMIT;

public record BlogPostFullDto (
    Long id,
    @NotEmpty(message = "Blog entry must have a title")
    String title,
    String authorUsername,
    @Size(min = 1, max = ENTRY_LIMIT, message = "Content cannot be empty or exceed " + ENTRY_LIMIT + "characters")
    String content,
    LocalDateTime createdAt
) {}

