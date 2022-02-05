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
@Table(name = "tag")
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull(groups = ValidationGroups.TagId.class)
    private Long tagId;
    @NotNull
    @Size(min = 5, max = 75)
    @Column(unique=true)
    private String title;

    @ManyToMany(mappedBy = "tags", fetch = FetchType.LAZY)
    private List<Post> posts;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tag tag = (Tag) o;
        return title.toLowerCase().equals(tag.title.toLowerCase());
    }

    @Override
    public int hashCode() {
        return Objects.hash(tagId, title, posts);
    }
}
