package com.codewithsaif.store.controllers;

import com.codewithsaif.store.dtos.ChangePasswordRequest;
import com.codewithsaif.store.dtos.RegisterUserRequest;
import com.codewithsaif.store.dtos.UpdateUserRequest;
import com.codewithsaif.store.dtos.UserDto;
import com.codewithsaif.store.mappers.UserMapper;
import com.codewithsaif.store.repositories.UserRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;

@AllArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    @GetMapping
    private Iterable<UserDto> getUsers(){
        return userRepository.findAll().stream()
                .map(userMapper::toDto)
                .toList();
    }

    @PostMapping
    private ResponseEntity<UserDto> registerUser(
            @Valid  @RequestBody RegisterUserRequest request,
            UriComponentsBuilder uriBuilder){
        var user = userMapper.toEntity(request);
        userRepository.save(user);
        var userDto = userMapper.toDto(user);
        var uri = uriBuilder.path("/users/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uri).body(userDto);
    }

    @GetMapping("/{id}")
    private ResponseEntity<UserDto> getUser(@PathVariable Long id){
        var user =  userRepository.findById(id).orElse(null);
        if(user == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(userMapper.toDto(user));
    }


    @PutMapping("/{id}")
    private ResponseEntity<UserDto> updateUser(
            @PathVariable(name = "id") Long id,
            @Valid @RequestBody UpdateUserRequest request){
        var user = userRepository.findById(id).orElse(null);
        if(user == null)
            return ResponseEntity.notFound().build();
         userMapper.toUpdate(request,user);
         userRepository.save(user);
       return ResponseEntity.ok(userMapper.toDto(user));
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<UserDto> deleteUer(@PathVariable(name = "id") Long id){
        var user =  userRepository.findById(id).orElse(null);
        if(user == null)
            return ResponseEntity.notFound().build();
        userRepository.delete(user);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/change-password")
    private ResponseEntity<Map<String,String>> changePassword(
            @PathVariable(name = "id") Long id,
            @Valid @RequestBody ChangePasswordRequest request){
        var user =  userRepository.findById(id).orElse(null);
        if(user == null)
            return ResponseEntity.notFound().build();
        if(!user.getPassword().equals(request.oldPassword)){
            return  new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        user.setPassword(request.newPassword);
        userRepository.save(user);
        return ResponseEntity.ok(Map.of("message", "Password saved successfully!"));
    }

}
