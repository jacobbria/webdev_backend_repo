package com.franklin.techblog.mapper;

import com.franklin.techblog.dto.BlogPostFullDto;
import com.franklin.techblog.dto.CreateBlogPostDto;
import com.franklin.techblog.model.BlogPost;
import com.franklin.techblog.entity.BlogUser;
import com.franklin.techblog.dto.BlogPostSummaryDto;

import static com.franklin.techblog.constants.BlogPostConstants.SUMMARY_LENGTH;

public class BlogPostMapper {
    public static BlogPost mapToBlogPost(CreateBlogPostDto dto, BlogUser author) {
        BlogPost post = new BlogPost();
        post.setTitle(dto.getTitle());
        post.setAuthor(author);
        post.setContent(dto.getContent());
        return post;
    }

    public static BlogPostFullDto mapToFullPostDto(BlogPost post) {
        return new BlogPostFullDto(
                post.getBlogpost_id(),
                post.getTitle(),
                post.getAuthor().getUsername(),
                post.getContent(),
                post.getCreatedAt()
        );
    }

    public static BlogPostSummaryDto mapToSummaryDto(BlogPost post) {
        String summary = getSummary(post.getContent());
        return new BlogPostSummaryDto(
                post.getBlogpost_id(),
                post.getTitle(),
                post.getAuthor().getUsername(),
                post.getCreatedAt(),
                summary
        );
    }

    private static String getSummary(String content) {
        return content.length() <= SUMMARY_LENGTH ? content : content.substring(0, SUMMARY_LENGTH) + "...";
    }
}
