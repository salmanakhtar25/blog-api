package com.blog.api.repository;

import com.blog.api.entities.Blogger;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;

public interface UserRepository extends JpaRepository<Blogger, Integer> {
    Optional<Blogger> findByEmail(String email);
}
