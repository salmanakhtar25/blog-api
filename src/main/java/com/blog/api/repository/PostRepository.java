package com.blog.api.repository;

import com.blog.api.entities.Blogger;
import com.blog.api.entities.Category;
import com.blog.api.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Integer> {
    List<Post> findByBlogger(Blogger blogger);
    List<Post> findByCategory(Category category);
    List<Post> findByTitleContaining(String title);
}
