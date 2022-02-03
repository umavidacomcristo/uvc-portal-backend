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
@Table(name = "tag")
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull(groups = ValidationGroups.TagId.class)
    private Long tagId;
    @NotNull
    @Size(min = 5, max = 75)
    private String title;

    @ManyToMany(mappedBy = "tags", fetch = FetchType.LAZY)
    private List<Post> posts;

}
