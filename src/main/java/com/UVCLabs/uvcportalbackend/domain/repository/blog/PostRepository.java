package com.UVCLabs.uvcportalbackend.domain.repository.blog;

import com.UVCLabs.uvcportalbackend.domain.models.blog.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {


}
