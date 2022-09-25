package com.blog.api.service.impl;

import com.blog.api.entities.Blogger;
import com.blog.api.entities.Category;
import com.blog.api.entities.Post;
import com.blog.api.exceptions.ResourceNotFoundException;
import com.blog.api.payloads.PostDTO;
import com.blog.api.payloads.PostResponse;
import com.blog.api.repository.CategoryRepository;
import com.blog.api.repository.PostRepository;
import com.blog.api.repository.UserRepository;
import com.blog.api.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public PostDTO createPost(PostDTO postDTO, Integer userId, Integer categoryId) {

        Blogger blogger = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "User Id", userId));
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "Category Id", categoryId));

        Post post = modelMapper.map(postDTO, Post.class);
        post.setImageName("default.png");
        post.setAddedDate(new Date());
        post.setBlogger(blogger);
        post.setCategory(category);

        Post savedPost= postRepository.save(post);
        return modelMapper.map(savedPost,PostDTO.class);
    }

    @Override
    public PostDTO updatePost(PostDTO postDTO, Integer postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "Post Id", postId));
        post.setTitle(postDTO.getTitle());
        post.setContent(postDTO.getContent());
        post.setImageName(postDTO.getImageName());

        Post updatedPost = postRepository.save(post);
        return modelMapper.map(updatedPost,PostDTO.class);
    }

    @Override
    public void deletePost(Integer postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "Post Id", postId));
        postRepository.delete(post);
    }

    @Override
    public PostResponse getAllPost(Integer pageNumber, Integer pageSize,String sortBy,String sortDirection) {
        Sort sort = (sortDirection.equalsIgnoreCase("asc"))? Sort.by(sortBy).ascending(): Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNumber,pageSize,sort);
        Page<Post> postPage = postRepository.findAll(pageable);
        List<Post> allPosts = postPage.getContent();
        List<PostDTO> postDTOs = allPosts.stream().map(post -> modelMapper.map(post, PostDTO.class)).collect(Collectors.toList());
        PostResponse postResponse = new PostResponse();
        postResponse.setContent(postDTOs);
        postResponse.setPageNumber(postPage.getNumber());
        postResponse.setPageSize(postPage.getSize());
        postResponse.setTotalElements(postPage.getTotalElements());
        postResponse.setTotalPages(postPage.getTotalPages());
        postResponse.setLastPage(postPage.isLast());
        return postResponse;
    }

    @Override
    public PostDTO getPostById(Integer postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "Post id", postId));
        return modelMapper.map(post,PostDTO.class);
    }

    @Override
    public List<PostDTO> getPostsByCategory(Integer categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "Category Id", categoryId));
        List<Post> posts = postRepository.findByCategory(category);
        return posts.stream().map(post -> modelMapper.map(post, PostDTO.class)).collect(Collectors.toList());
    }

    @Override
    public List<PostDTO> getPostsByUser(Integer userId) {
        Blogger blogger = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "User Id", userId));
        List<Post> posts = postRepository.findByBlogger(blogger);
        return posts.stream().map(post -> modelMapper.map(post,PostDTO.class)).collect(Collectors.toList());
    }

    @Override
    public List<PostDTO> searchPosts(String keyword) {
        List<Post> posts = postRepository.findByTitleContaining(keyword);
        return posts.stream().map(post -> modelMapper.map(post, PostDTO.class)).collect(Collectors.toList());
    }
}
