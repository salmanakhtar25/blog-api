package com.blog.api.service.impl;

import com.blog.api.entities.User;
import com.blog.api.payloads.UserDTO;
import com.blog.api.repository.UserRepository;
import com.blog.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDTO createUser(UserDTO userDTO) {

        User user = this.dtoToUser(userDTO);
        User savedUser = userRepository.save(user);
        return userToDto(savedUser);
    }

    @Override
    public UserDTO updateUser(UserDTO user, Integer userId) {
        return null;
    }

    @Override
    public UserDTO getUserById(Integer userId) {
        return null;
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return null;
    }

    @Override
    public void deleteUser(Integer userId) {

    }

    private User dtoToUser(UserDTO userDTO){
        User user  = new User();
        user.setName(userDTO.getName());
        user.setId(userDTO.getId());
        user.setEmail(userDTO.getEmail());
        user.setAbout(userDTO.getAbout());
        user.setPassword(userDTO.getPassword());
       return user;
    }

    private UserDTO userToDto(User user){
        UserDTO userDTO  = new UserDTO();
        userDTO.setName(user.getName());
        userDTO.setId(user.getId());
        userDTO.setEmail(user.getEmail());
        userDTO.setAbout(user.getAbout());
        userDTO.setPassword(user.getPassword());
        return userDTO;
    }
}
