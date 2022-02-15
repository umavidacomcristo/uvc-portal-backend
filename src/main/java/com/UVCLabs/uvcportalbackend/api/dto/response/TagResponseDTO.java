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
public class TagResponseDTO {

    @Id
    @NotEmpty
    private Long tagId;
    @NotEmpty
    @Size(min = 5, max = 75)
    private String title;

    public TagResponseDTO(String title) {
        this.title = title;
    }
}
