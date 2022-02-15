package com.UVCLabs.uvcportalbackend.api.dto.response;

import com.UVCLabs.uvcportalbackend.domain.ValidationGroups;
import com.UVCLabs.uvcportalbackend.domain.models.blog.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
public class UserResponseDTO {

    @NotEmpty
    private Long id;
    @NotEmpty
    private String firstName;
    @NotEmpty
    private String lastName;
    @NotEmpty
    private String email;
    @NotEmpty
    private LocalDateTime registeredAt;
    @NotEmpty
    private String intro;
    @NotEmpty
    private LocalDateTime lastLogin;

    @NotEmpty
    private boolean isAdmin;
    private List<PostResponseDTO> posts;

    public UserResponseDTO(Long id, String firstName, String lastName, String email, LocalDateTime registeredAt, String intro, LocalDateTime lastLogin, boolean isAdmin, List<PostResponseDTO> posts) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.registeredAt = registeredAt;
        this.intro = intro;
        this.lastLogin = lastLogin;
        this.isAdmin = isAdmin;
        this.posts = posts;
    }
}
