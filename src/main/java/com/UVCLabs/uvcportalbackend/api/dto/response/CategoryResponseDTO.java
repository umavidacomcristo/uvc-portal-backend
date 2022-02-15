package com.UVCLabs.uvcportalbackend.api.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
public class CategoryResponseDTO {
    @NotEmpty
    @Id
    private Long categoryId;
    @NotEmpty
    @Size(min = 5, max = 75)
    private String title;
    @NotEmpty
    @Size(min = 5, max = 75)
    private String metaTitle;

    public CategoryResponseDTO(String title, String metaTitle) {
        this.title = title;
        this.metaTitle = metaTitle;
    }
}
