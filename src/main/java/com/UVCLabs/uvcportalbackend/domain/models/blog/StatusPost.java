package com.UVCLabs.uvcportalbackend.domain.models.blog;

import com.UVCLabs.uvcportalbackend.domain.ValidationGroups;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Getter
@Setter
public class StatusPost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull(groups = ValidationGroups.StatusPostId.class)
    private Long statusPostId;

    @NotNull
    @Size(min = 2, max = 75)
    private String statusName;

    @OneToMany(mappedBy = "statusPost")
    private List<Post> posts;
}
