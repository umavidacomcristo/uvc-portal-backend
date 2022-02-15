package com.UVCLabs.uvcportalbackend.domain.models;

import com.UVCLabs.uvcportalbackend.domain.ValidationGroups;
import com.UVCLabs.uvcportalbackend.domain.models.blog.Post;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@Table(name = "portal_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull(groups = ValidationGroups.UserId.class)
    private Long id;

    @NotBlank
    @Size(max = 20)
    private String firstName;
    @NotNull
    @Size(max = 20)
    private String lastName;
    @NotNull
    @Email
    @Size(max = 50)
    private String email;
    @NotNull
    @Size(max = 255)
    private String password;
    @NotNull
    private LocalDateTime registeredAt;
    @NotNull
    @Size(max = 50)
    private String intro;
    private LocalDateTime lastLogin;

    @NotNull
    private boolean isAdmin;

    @OneToMany(mappedBy = "user")
    private List<Post> posts;

    @Override
    public int hashCode(){
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj){
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        User other = (User) obj;
        return Objects.equals(id, other.id);
    }
}
