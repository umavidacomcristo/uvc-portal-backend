package com.UVCLabs.uvcportalbackend.domain.service;

import com.UVCLabs.uvcportalbackend.api.models.requests.CategoryRegisterDTO;
import com.UVCLabs.uvcportalbackend.api.models.requests.TagRegisterDTO;
import com.UVCLabs.uvcportalbackend.domain.exception.BusinessException;
import com.UVCLabs.uvcportalbackend.domain.models.blog.Category;
import com.UVCLabs.uvcportalbackend.domain.models.blog.Tag;
import com.UVCLabs.uvcportalbackend.domain.repository.blog.CategoryRepository;
import com.UVCLabs.uvcportalbackend.domain.repository.blog.PostRepository;
import com.UVCLabs.uvcportalbackend.domain.repository.blog.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogService {

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private TagRepository tagRepository;
    @Autowired
    private PostRepository postRepository;

    public Category saveCategory(Category category){
        Category categoryExists = categoryRepository.findByTitle(category.getTitle());
        if(categoryExists !=null && categoryExists.equals(category) &&
                categoryExists.getTitle().toLowerCase().equals(category.getTitle())){
            throw new BusinessException("Category already exists on database");
        }
        return categoryRepository.save(category);
    }

    public Tag saveTag(Tag tag){
        Tag tagExists = tagRepository.findByTitle(tag.getTitle());
        if(tagExists !=null && tagExists.equals(tag)&&
                tagExists.getTitle().toLowerCase().equals(tag.getTitle())){
            throw new BusinessException("Tag already exists on database");
        }
        return tagRepository.save(tag);
    }


    public Category updateCategory(Category category, CategoryRegisterDTO categoryDTO){
        if(category!= null && categoryDTO!= null){
            category.setTitle(categoryDTO.getTitle());
            category.setMetaTitle(categoryDTO.getMetaTitle());
            return categoryRepository.save(category);
        }
        throw new BusinessException("Category cannot be updated");
    }

    public Tag updateTag(Tag tag, TagRegisterDTO tagDTO){
        if(tag!= null && tagDTO!= null){
            tag.setTitle(tagDTO.getTitle());
            return tagRepository.save(tag);
        }
        throw new BusinessException("Tag cannot be updated");
    }

}
