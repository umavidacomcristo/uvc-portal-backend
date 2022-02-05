package com.UVCLabs.uvcportalbackend.domain.models.blog;

import com.UVCLabs.uvcportalbackend.domain.ValidationGroups;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Objects;

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
    @Column(unique=true)
    private String metaTitle;

    @OneToMany(mappedBy = "category")
    private List<Post> posts;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return title.toLowerCase().equals(category.title.toLowerCase());
    }

    @Override
    public int hashCode() {
        return Objects.hash(categoryId, title);
    }
}
