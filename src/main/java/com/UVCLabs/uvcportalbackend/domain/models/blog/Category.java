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
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull(groups = ValidationGroups.CategoryId.class)
    private Long categoryId;
    @NotNull
    @Size(min = 5, max = 75)
    private String title;
    @NotNull
    @Size(min = 5, max = 75)
    private String metaTitle;

    @OneToMany(mappedBy = "category")
    private List<Post> posts;
}
