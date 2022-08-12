package com.blog.api.service.impl;

import com.blog.api.entities.User;
import com.blog.api.payloads.UserDTO;
import com.blog.api.repository.UserRepository;
import com.blog.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import  com.blog.api.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
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
    public UserDTO updateUser(UserDTO userDTO, Integer userId) {

        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User"," id ", userId));

        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setAbout(userDTO.getAbout());

        User updateUser = userRepository.save(user);
        return userToDto(updateUser);
    }

    @Override
    public UserDTO getUserById(Integer userId) {

        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", " id ", userId));

        return userToDto(user);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();

        return users.stream().map(this::userToDto).collect(Collectors.toList());
    }

    @Override
    public void deleteUser(Integer userId) {
       User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException(" User ", " Id ", userId));
       userRepository.delete(user);
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
