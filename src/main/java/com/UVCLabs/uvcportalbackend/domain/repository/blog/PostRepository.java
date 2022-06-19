package com.UVCLabs.uvcportalbackend.domain.repository.blog;

import com.UVCLabs.uvcportalbackend.domain.models.blog.Post;
import com.UVCLabs.uvcportalbackend.domain.models.blog.StatusPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    Post findByTitle(String title);
    @Query("select p from Post p where p.statusPost.statusPostId = :id")
    List<Post> findAllByStatusPost(@Param("id") Long id);
}
