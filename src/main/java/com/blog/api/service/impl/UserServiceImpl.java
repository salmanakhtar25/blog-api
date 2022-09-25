package com.blog.api.service.impl;

import com.blog.api.config.Constants;
import com.blog.api.entities.Blogger;
import com.blog.api.entities.Role;
import com.blog.api.payloads.UserDTO;
import com.blog.api.repository.RoleRepository;
import com.blog.api.repository.UserRepository;
import com.blog.api.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import  com.blog.api.exceptions.ResourceNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public UserDTO registerNewUser(UserDTO userDTO) {
        Blogger blogger = modelMapper.map(userDTO, Blogger.class);
        blogger.setPassword(passwordEncoder.encode(blogger.getPassword()));
        Role role = roleRepository.findById(Constants.NORMAL_USER).get();
        blogger.getRoles().add(role);
        Blogger savedUser = userRepository.save(blogger);
        return modelMapper.map(savedUser,UserDTO.class);
    }

    @Override
    public UserDTO createUser(UserDTO userDTO) {

        Blogger user = this.dtoToUser(userDTO);
        Blogger savedUser = userRepository.save(user);
        return userToDto(savedUser);
    }

    @Override
    public UserDTO updateUser(UserDTO userDTO, Integer userId) {

        Blogger user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User"," id ", userId));

        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setAbout(userDTO.getAbout());

        Blogger updateUser = userRepository.save(user);
        return userToDto(updateUser);
    }

    @Override
    public UserDTO getUserById(Integer userId) {

        Blogger user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", " id ", userId));

        return userToDto(user);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<Blogger> users = userRepository.findAll();

        return users.stream().map(this::userToDto).collect(Collectors.toList());
    }

    @Override
    public void deleteUser(Integer userId) {
       Blogger user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException(" User ", " Id ", userId));
       userRepository.delete(user);
    }

    private Blogger dtoToUser(UserDTO userDTO){
        return modelMapper.map(userDTO,Blogger.class);
    }

    private UserDTO userToDto(Blogger user){
        return modelMapper.map(user,UserDTO.class);
    }
}
