package com.blog.api;

import com.blog.api.config.Constants;
import com.blog.api.entities.Role;
import com.blog.api.repository.RoleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class BlogApiApplication implements CommandLineRunner {

    @Autowired
    private RoleRepository roleRepository;

    public static void main(String[] args) {
        SpringApplication.run(BlogApiApplication.class, args);
    }

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }

    @Override
    public void run(String... args) throws Exception {
         try {
             Role admin = new Role();
             admin.setId(Constants.ADMIN_USER);
             admin.setName("ROLE_ADMIN");
             Role normal = new Role();
             normal.setId(Constants.NORMAL_USER);
             normal.setName("ROLE_NORMAL");
             List<Role> roles = List.of(admin, normal);
             roleRepository.saveAll(roles);

         }catch (Exception e){
              e.getStackTrace();
         }
    }
}
