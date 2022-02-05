package com.UVCLabs.uvcportalbackend.api.models.response;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
public class PostResponseDTO {

    @Id
    @NotEmpty
    private Long postId;

    @NotEmpty
    @Size(min = 5, max = 75)
    private String title;
    @NotEmpty
    @Size(min = 5, max = 100)
    private String summary;
    @NotEmpty
    private String postContent;
    @NotEmpty
    private LocalDateTime createdAt;
    @NotEmpty
    private LocalDateTime updatedAt;
    @NotEmpty
    private LocalDateTime publishedAt;
    @NotEmpty
    private StatusPostResponseDTO statusPost;

    @NumberFormat
    private Long userId;
    @NumberFormat
    private Long categoryId;
    @NotEmpty
    private List<TagResponseDTO> tags;

    public PostResponseDTO(Long postId, String title, String summary, String postContent, LocalDateTime createdAt, LocalDateTime updatedAt, LocalDateTime publishedAt, StatusPostResponseDTO statusPost, Long userId, Long categoryId, List<TagResponseDTO> tags) {
        this.postId = postId;
        this.title = title;
        this.summary = summary;
        this.postContent = postContent;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.publishedAt = publishedAt;
        this.statusPost = statusPost;
        this.userId = userId;
        this.categoryId = categoryId;
        this.tags = tags;
    }
}
