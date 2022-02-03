package com.UVCLabs.uvcportalbackend.domain.repository.blog;

import com.UVCLabs.uvcportalbackend.domain.models.blog.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {


}
