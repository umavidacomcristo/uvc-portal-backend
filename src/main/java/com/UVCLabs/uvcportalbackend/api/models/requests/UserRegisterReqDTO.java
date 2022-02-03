package com.UVCLabs.uvcportalbackend.api.models.requests;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
public class UserRegisterReqDTO {
    @NotEmpty
    @Size(min = 2, max = 20)
    private String firstName;
    @NotEmpty
    @Size(min = 2, max = 20)
    private String lastName;
    @NotEmpty
    @Size(min = 10, max = 10)
    private String password;

    @Email
    @NotEmpty
    @Size(max = 50)
    private String email;

    @NotEmpty
    @Size(max = 50)
    private String intro;

    public UserRegisterReqDTO(String firstName, String lastName, String password, String email, String intro) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.email = email;
        this.intro = intro;
    }
}
