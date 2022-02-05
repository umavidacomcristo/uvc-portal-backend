package com.UVCLabs.uvcportalbackend.domain.repository.blog;

import com.UVCLabs.uvcportalbackend.domain.models.blog.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    Category findByTitle(String title);
}
