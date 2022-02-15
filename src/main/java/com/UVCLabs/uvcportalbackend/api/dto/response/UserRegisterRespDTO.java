package com.UVCLabs.uvcportalbackend.api.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
public class UserRegisterRespDTO {
    @NotEmpty
    @Size(min = 2, max = 20)
    private String firstName;
    @NotEmpty
    @Size(min = 2, max = 20)
    private String lastName;

    @Email
    @NotEmpty
    @Size(max = 50)
    private String email;

    @NotEmpty
    @Size(max = 50)
    private String intro;

    public UserRegisterRespDTO(String firstName, String lastName, String email, String intro) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.intro = intro;
    }
}
