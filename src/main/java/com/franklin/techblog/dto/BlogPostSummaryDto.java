package com.franklin.techblog.dto;

import lombok.Data;

import java.time.LocalDateTime;

public record BlogPostSummaryDto(
        Long id,
        String title,
        String authorUsername,
        LocalDateTime createdAt,
        String summary
) {}
