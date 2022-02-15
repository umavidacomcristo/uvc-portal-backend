package com.UVCLabs.uvcportalbackend.api.dto.requests;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
public class CategoryRegisterDTO {
    @NotEmpty
    @Size(min = 5, max = 75)
    private String title;
    @NotEmpty
    @Size(min = 5, max = 75)
    private String metaTitle;

    public CategoryRegisterDTO(String title, String metaTitle) {
        this.title = title;
        this.metaTitle = metaTitle;
    }
}
