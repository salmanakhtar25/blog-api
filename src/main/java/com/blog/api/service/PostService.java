package com.blog.api.service;

import com.blog.api.entities.Post;
import com.blog.api.payloads.PostDTO;

import java.util.List;

public interface PostService {
    PostDTO createPost(PostDTO postDTO,Integer userId, Integer categoryId);
    PostDTO updatePost(PostDTO postDTO, Integer postId);
    void deletePost(Integer postId);
    List<PostDTO> getAllPost();
    PostDTO getPostById(Integer postId);
    List<PostDTO> getPostsByCategory(Integer categoryId);
    List<PostDTO> getPostsByUser(Integer userId);
    List<PostDTO> searchPosts(String keyword);
}
