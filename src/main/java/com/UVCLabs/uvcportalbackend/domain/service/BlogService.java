package com.UVCLabs.uvcportalbackend.domain.service;

import com.UVCLabs.uvcportalbackend.api.dto.requests.CategoryRegisterDTO;
import com.UVCLabs.uvcportalbackend.api.dto.requests.PostRegisterDTO;
import com.UVCLabs.uvcportalbackend.api.dto.requests.PostStatusRequestDTO;
import com.UVCLabs.uvcportalbackend.api.dto.requests.TagRegisterDTO;
import com.UVCLabs.uvcportalbackend.domain.exception.BusinessException;
import com.UVCLabs.uvcportalbackend.domain.models.User;
import com.UVCLabs.uvcportalbackend.domain.models.blog.Category;
import com.UVCLabs.uvcportalbackend.domain.models.blog.Post;
import com.UVCLabs.uvcportalbackend.domain.models.blog.StatusPost;
import com.UVCLabs.uvcportalbackend.domain.models.blog.Tag;
import com.UVCLabs.uvcportalbackend.domain.repository.UserRepository;
import com.UVCLabs.uvcportalbackend.domain.repository.blog.CategoryRepository;
import com.UVCLabs.uvcportalbackend.domain.repository.blog.PostRepository;
import com.UVCLabs.uvcportalbackend.domain.repository.blog.StatusPostRepository;
import com.UVCLabs.uvcportalbackend.domain.repository.blog.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BlogService {

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private TagRepository tagRepository;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private StatusPostRepository statusPostRepository;

    @Autowired
    private UserRepository userRepository;

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

    public Post savePost(PostRegisterDTO postRegisterDTO){
        Post postExists = postRepository.findByTitle(postRegisterDTO.getTitle());
        if(postExists !=null && postExists.equals(postRegisterDTO)&&
                postExists.getTitle().toLowerCase().equals(postRegisterDTO.getTitle())){
            throw new BusinessException("Post with same title already exists on database");
        }

        List<Tag> tags = tagRepository.findByIds(postRegisterDTO.getTagsId());
        Optional<Category> category = categoryRepository.findById(postRegisterDTO.getCategoryId());
        Optional<StatusPost> statusPost = Optional.ofNullable(statusPostRepository.findBystatusName("DRAFT"));
        Optional<User> user = userRepository.findById(postRegisterDTO.getUserId());
        if(tags == null && category.isEmpty()){
            throw new BusinessException("Tags or categories doesn't exists on database");
        }

        if(statusPost.isEmpty()){
            throw new BusinessException("Status post default not found on database");
        }

        if(user.isEmpty()){
            throw new BusinessException("User not found on database");
        }
        Post post = new Post();
        post.setTags(tags);
        post.setCategory(category.get());
        post.setStatusPost(statusPost.get());
        post.setCreatedAt(LocalDateTime.now());
        post.setUpdatedAt(LocalDateTime.now());
        post.setSummary(postRegisterDTO.getSummary());
        post.setTitle(postRegisterDTO.getTitle());
        post.setPostContent(postRegisterDTO.getPostContent());
        post.setUser(user.get());
        return postRepository.save(post);
    }

    public Post setStatusPost(PostStatusRequestDTO postStatusRequestDTO){
        Optional<Post> postExists = postRepository.findById(Long.valueOf(postStatusRequestDTO.getPostId()));
        Optional<StatusPost> statusPost = statusPostRepository.findById(Long.valueOf(postStatusRequestDTO.getStatusPostId()));

        if(postExists.isPresent() && statusPost.isPresent()){
            Post post = postExists.get();
            if(statusPost.get().getStatusName().equals("PUBLISHED")){
                post.setPublishedAt(LocalDateTime.now());
            }
            if(statusPost.get().getStatusName().equals("DRAFT")){
                post.setPublishedAt(null);
            }

            post.setStatusPost(statusPost.get());
            return postRepository.save(post);
        }

        throw new BusinessException("Cannot set post status");
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

    public List<Post> getPublishedPost(String statusName){
        Optional<StatusPost> statusPost = Optional.ofNullable(statusPostRepository.findBystatusName(statusName));
        List<Post> posts = new ArrayList<>();

        if(statusPost.isPresent()){
            posts = postRepository.findAllByStatusPost(statusPost.get().getStatusPostId());
        }else{
            throw new BusinessException("Error during getting posts with status " + statusName);
        }

        return posts;
    }

}
