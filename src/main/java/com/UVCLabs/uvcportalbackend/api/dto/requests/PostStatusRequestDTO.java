package com.UVCLabs.uvcportalbackend.api.dto.requests;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.NotEmpty;


@Getter
@Setter
@NoArgsConstructor
public class PostStatusRequestDTO {

    @NotEmpty
    @NumberFormat
    private String postId;

    @NotEmpty
    @NumberFormat
    private String statusPostId;

    public PostStatusRequestDTO(String postId, String statusPostId) {
        this.postId = postId;
        this.statusPostId = statusPostId;
    }
}
