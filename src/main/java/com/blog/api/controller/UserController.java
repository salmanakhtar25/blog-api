package com.blog.api.controller;

import com.blog.api.payloads.ApiResponse;
import com.blog.api.payloads.UserDTO;
import com.blog.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/")
    public ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserDTO userDTO){
        UserDTO createdUser = userService.createUser(userDTO);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDTO> getUser(@PathVariable Integer userId){
        return ResponseEntity.ok(userService.getUserById(userId));
   }

   @DeleteMapping("/{userId}")
   public ResponseEntity<ApiResponse> deleteUser(@PathVariable Integer userId){
        userService.deleteUser(userId);
        return new ResponseEntity<>(new ApiResponse("User deleted Successfully", true), HttpStatus.OK);
   }

   @PutMapping("/{userId}")
   public ResponseEntity<UserDTO> updateUser(@Valid @RequestBody UserDTO userDTO,@PathVariable Integer userId){
       UserDTO updatedUser = userService.updateUser(userDTO, userId);
       return ResponseEntity.ok(updatedUser);
   }

   @GetMapping("/")
   public ResponseEntity<List<UserDTO>> getAllUsers(){
       return ResponseEntity.ok(userService.getAllUsers());
   }

}
