package com.UVCLabs.uvcportalbackend.api.controller;

import com.UVCLabs.uvcportalbackend.UvcPortalBackendApplication;
import com.UVCLabs.uvcportalbackend.api.models.requests.CategoryRegisterDTO;
import com.UVCLabs.uvcportalbackend.api.models.requests.TagRegisterDTO;
import com.UVCLabs.uvcportalbackend.api.models.requests.UserRegisterReqDTO;
import com.UVCLabs.uvcportalbackend.api.models.requests.UserUpdateReqDTO;
import com.UVCLabs.uvcportalbackend.api.models.response.UserRegisterRespDTO;
import com.UVCLabs.uvcportalbackend.domain.models.User;
import com.UVCLabs.uvcportalbackend.domain.models.blog.Category;
import com.UVCLabs.uvcportalbackend.domain.models.blog.Tag;
import com.UVCLabs.uvcportalbackend.domain.repository.UserRepository;
import com.UVCLabs.uvcportalbackend.domain.repository.blog.CategoryRepository;
import com.UVCLabs.uvcportalbackend.domain.repository.blog.PostRepository;
import com.UVCLabs.uvcportalbackend.domain.repository.blog.TagRepository;
import com.UVCLabs.uvcportalbackend.domain.service.BlogService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/blog")
public class BlogController {
    private static final Logger LOGGER= LoggerFactory.getLogger(UvcPortalBackendApplication.class);
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private TagRepository tagRepository;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private BlogService blogService;
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/tag")
    public List<Tag> listAllTags(){
        return tagRepository.findAll();
    }

    @GetMapping("/category")
    public List<Category> listAllCategories(){
        return categoryRepository.findAll();
    }

    @PostMapping("/category")
    @ResponseStatus(HttpStatus.CREATED)
    public Category addCategory(@Valid @RequestBody CategoryRegisterDTO categoryRegisterDTO){
        return blogService.saveCategory(toDomainCategory(categoryRegisterDTO));
    }

    @PostMapping("/tag")
    @ResponseStatus(HttpStatus.CREATED)
    public Tag addTag(@Valid @RequestBody TagRegisterDTO tagRegisterDTO){
        return blogService.saveTag(toDomainTag(tagRegisterDTO));
    }

    @PutMapping("/category/{categoryId}")
    public ResponseEntity<Category> updateCategory(@Valid  @PathVariable Long categoryId, @RequestBody CategoryRegisterDTO categoryUpdateReqDTO){
        Optional<Category> category = categoryRepository.findById(categoryId);
        if(!category.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        LOGGER.info("Updating category with id {}", categoryId);
        Category updatedCategory = blogService.updateCategory(category.get(), categoryUpdateReqDTO);
        LOGGER.info("Category updated successfully");
        return ResponseEntity.ok(updatedCategory);
    }

    @PutMapping("/tag/{tagId}")
    public ResponseEntity<Tag> updateTag(@Valid  @PathVariable Long tagId, @RequestBody TagRegisterDTO tagRegisterDTO){
        Optional<Tag> tag = tagRepository.findById(tagId);
        if(!tag.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        LOGGER.info("Updating tag with id {}", tag);
        Tag updatedTag = blogService.updateTag(tag.get(), tagRegisterDTO);
        LOGGER.info("Tag updated successfully");
        return ResponseEntity.ok(updatedTag);
    }

    private Category toDomainCategory(CategoryRegisterDTO categoryRegisterDTO) {
        return modelMapper.map(categoryRegisterDTO, Category.class);
    }
    private Tag toDomainTag(TagRegisterDTO tagRegisterDTO) {
        return modelMapper.map(tagRegisterDTO, Tag.class);
    }
}
