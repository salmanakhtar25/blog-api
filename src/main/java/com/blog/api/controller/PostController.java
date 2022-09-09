package com.blog.api.controller;

import com.blog.api.config.Constants;
import com.blog.api.payloads.ApiResponse;
import com.blog.api.payloads.PostDTO;
import com.blog.api.payloads.PostResponse;
import com.blog.api.repository.PostRepository;
import com.blog.api.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping("/user/{userId}/category/{categoryId}/posts")
    public ResponseEntity<PostDTO> createPost(
            @RequestBody PostDTO postDTO,
            @PathVariable Integer userId    ,
            @PathVariable Integer categoryId
    ){
        PostDTO post = postService.createPost(postDTO, userId, categoryId);
        return new ResponseEntity<>(post, HttpStatus.CREATED);
    }

    @GetMapping("/user/{userId}/posts")
    public ResponseEntity<List<PostDTO>> getPostsByUser(@PathVariable Integer userId){
        List<PostDTO> postsByUser = postService.getPostsByUser(userId);
        return new ResponseEntity<>(postsByUser,HttpStatus.OK);
    }

    @GetMapping("/category/{categoryId}/posts")
    public ResponseEntity<List<PostDTO>> getPostsByCategory(@PathVariable Integer categoryId){
        List<PostDTO> postsByCategory = postService.getPostsByCategory(categoryId);
        return new ResponseEntity<>(postsByCategory,HttpStatus.OK);
    }

    @GetMapping("/posts")
    public ResponseEntity<PostResponse> getAllPosts( @RequestParam(value = "pageNumber",defaultValue = Constants.PAGE_NUMBER,required = false) Integer pageNumber,
                                                     @RequestParam(value = "pageSize",defaultValue = Constants.PAGE_SIZE,required = false) Integer pageSize,
                                                     @RequestParam(value = "sortBy",defaultValue = Constants.SORT_BY,required = false) String sortBy,
                                                     @RequestParam(value = "sortDirection",defaultValue = Constants.SORT_DIRECTION,required = false) String sortDirection){
        PostResponse allPost = postService.getAllPost(pageNumber,pageSize,sortBy,sortDirection);
        return new ResponseEntity<>(allPost,HttpStatus.OK);
    }

    @GetMapping("/posts/{postId}")
    public ResponseEntity<PostDTO> getPost(@PathVariable Integer postId){
        PostDTO post = postService.getPostById(postId);
        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    @DeleteMapping("/posts/{postId}")
    public ResponseEntity<ApiResponse> deletePost(@PathVariable Integer postId){
        postService.deletePost(postId);
        return new ResponseEntity<>(new ApiResponse("Post deleted succesfully",true),HttpStatus.OK);
    }

    @PutMapping("/posts/{postId}")
    public ResponseEntity<PostDTO> updatePost(@RequestBody PostDTO postDTO, @PathVariable Integer postId){
        PostDTO updatedPost = postService.updatePost(postDTO, postId);
        return new ResponseEntity<>(updatedPost,HttpStatus.OK);
    }

    @GetMapping("/posts/search/{keyword}")
    public ResponseEntity<List<PostDTO>> searchPostByTitle(@PathVariable("keyword") String keyword){
        List<PostDTO> posts = postService.searchPosts(keyword);
        return new ResponseEntity<>(posts,HttpStatus.OK);
    }

}
