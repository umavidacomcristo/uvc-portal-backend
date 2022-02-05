package com.UVCLabs.uvcportalbackend.api.models.requests;

import com.UVCLabs.uvcportalbackend.domain.models.blog.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

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
