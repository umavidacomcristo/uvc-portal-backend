package com.UVCLabs.uvcportalbackend.api.models.requests;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
public class PostRegisterDTO {

    @NotEmpty
    @Size(min = 5, max = 75)
    private String title;
    @NotEmpty
    @Size(min = 5, max = 100)
    private String summary;
    @NotEmpty
    private String postContent;

    @NumberFormat
    private Long userId;
    @NumberFormat
    private Long categoryId;
    @NotEmpty
    private List<Long> tagsId;

    public PostRegisterDTO(String title, String summary, String postContent, Long categoryId, List<Long> tagsId, Long userId) {
        this.title = title;
        this.summary = summary;
        this.postContent = postContent;
        this.categoryId = categoryId;
        this.tagsId = tagsId;
        this.userId = userId;
    }
}
