package com.UVCLabs.uvcportalbackend.api.controller;

import com.UVCLabs.uvcportalbackend.UvcPortalBackendApplication;
import com.UVCLabs.uvcportalbackend.api.models.requests.*;
import com.UVCLabs.uvcportalbackend.api.models.response.CategoryResponseDTO;
import com.UVCLabs.uvcportalbackend.api.models.response.PostResponseDTO;
import com.UVCLabs.uvcportalbackend.api.models.response.TagResponseDTO;
import com.UVCLabs.uvcportalbackend.api.models.response.UserRegisterRespDTO;
import com.UVCLabs.uvcportalbackend.domain.models.User;
import com.UVCLabs.uvcportalbackend.domain.models.blog.Category;
import com.UVCLabs.uvcportalbackend.domain.models.blog.Post;
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
import java.util.stream.Collectors;

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
    public List<TagResponseDTO> listAllTags(){
        return toTagCollectionDTO(tagRepository.findAll());
    }

    @GetMapping("/category")
    public List<CategoryResponseDTO> listAllCategories(){
        return toCategoryCollectionDTO(categoryRepository.findAll());
    }

    @GetMapping("/post")
    public List<PostResponseDTO> listAllPosts(){
        return toPostCollectionDTO(postRepository.findAll());
    }

    @PostMapping("/post/create")
    @ResponseStatus(HttpStatus.CREATED)
    public PostResponseDTO addPost(@Valid @RequestBody PostRegisterDTO postRegisterDTO){
        return toPostDTO(blogService.savePost(postRegisterDTO));
    }

    @PutMapping("/post/updateStatus")
    @ResponseStatus(HttpStatus.OK)
    public PostResponseDTO modifyPostStatus(@Valid @RequestBody PostStatusRequestDTO postStatusRequestDTO){
        return toPostDTO(blogService.setStatusPost(postStatusRequestDTO));
    }

    @PostMapping("/category")
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryResponseDTO addCategory(@Valid @RequestBody CategoryRegisterDTO categoryRegisterDTO){
        LOGGER.info("Creating a new category!");
        return toCategoryDTO(blogService.saveCategory(toDomainCategory(categoryRegisterDTO)));
    }

    @PostMapping("/tag")
    @ResponseStatus(HttpStatus.CREATED)
    public TagResponseDTO addTag(@Valid @RequestBody TagRegisterDTO tagRegisterDTO){
        LOGGER.info("Creating a new tag!");
        return toTagDTO(blogService.saveTag(toDomainTag(tagRegisterDTO)));
    }

    @PutMapping("/category/{categoryId}")
    public ResponseEntity<CategoryResponseDTO> updateCategory(@Valid  @PathVariable Long categoryId, @RequestBody CategoryRegisterDTO categoryUpdateReqDTO){
        Optional<Category> category = categoryRepository.findById(categoryId);
        if(!category.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        LOGGER.info("Updating category with id {}", categoryId);
        Category updatedCategory = blogService.updateCategory(category.get(), categoryUpdateReqDTO);
        LOGGER.info("Category updated successfully");
        return ResponseEntity.ok(toCategoryDTO(updatedCategory));
    }

    @PutMapping("/tag/{tagId}")
    public ResponseEntity<TagResponseDTO> updateTag(@Valid  @PathVariable Long tagId, @RequestBody TagRegisterDTO tagRegisterDTO){
        Optional<Tag> tag = tagRepository.findById(tagId);
        if(!tag.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        LOGGER.info("Updating tag with id {}", tag);
        Tag updatedTag = blogService.updateTag(tag.get(), tagRegisterDTO);
        LOGGER.info("Tag updated successfully");
        return ResponseEntity.ok(toTagDTO(updatedTag));
    }

    private Category toDomainCategory(CategoryRegisterDTO categoryRegisterDTO) {
        return modelMapper.map(categoryRegisterDTO, Category.class);
    }
    private Tag toDomainTag(TagRegisterDTO tagRegisterDTO) {
        return modelMapper.map(tagRegisterDTO, Tag.class);
    }
    private TagResponseDTO toTagDTO(Tag tag){
        return modelMapper.map(tag, TagResponseDTO.class);
    }

    private CategoryResponseDTO toCategoryDTO(Category category){
        return modelMapper.map(category, CategoryResponseDTO.class);
    }

    private PostResponseDTO toPostDTO(Post post){
        return modelMapper.map(post, PostResponseDTO.class);
    }

    private List<TagResponseDTO> toTagCollectionDTO(List<Tag> tag){
        return tag.stream()
                .map(tagAux -> toTagDTO(tagAux))
                .collect(Collectors.toList());
    }

    private List<CategoryResponseDTO> toCategoryCollectionDTO(List<Category> category){
        return category.stream()
                .map(categoryAux -> toCategoryDTO(categoryAux))
                .collect(Collectors.toList());
    }

    private List<PostResponseDTO> toPostCollectionDTO(List<Post> post){
        return post.stream()
                .map(postAux -> toPostDTO(postAux))
                .collect(Collectors.toList());
    }

}
