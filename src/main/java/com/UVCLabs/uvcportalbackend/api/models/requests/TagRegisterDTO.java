package com.UVCLabs.uvcportalbackend.api.models.requests;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


@Getter
@Setter
@NoArgsConstructor
public class TagRegisterDTO {

    @NotEmpty
    @Size(min = 2, max = 75)
    private String title;

    public TagRegisterDTO(String title) {
        this.title = title;
    }
}
