package com.UVCLabs.uvcportalbackend.api.models.requests;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
public class UserUpdateReqDTO {
    @NotEmpty
    @Size(min = 2, max = 20)
    private String firstName;
    @NotEmpty
    @Size(min = 2, max = 20)
    private String lastName;

    @NotEmpty
    @Size(max = 50)
    private String intro;

    public UserUpdateReqDTO(String firstName, String lastName, String intro) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.intro = intro;
    }
}
