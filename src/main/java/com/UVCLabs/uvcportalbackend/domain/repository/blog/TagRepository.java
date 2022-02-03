package com.UVCLabs.uvcportalbackend.domain.repository.blog;

import com.UVCLabs.uvcportalbackend.domain.models.blog.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {


}
