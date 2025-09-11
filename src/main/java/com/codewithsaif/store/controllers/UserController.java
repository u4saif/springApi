package com.codewithsaif.store.controllers;

import com.codewithsaif.store.dtos.RegisterUserRequest;
import com.codewithsaif.store.dtos.UpdateUserRequest;
import com.codewithsaif.store.dtos.UserDto;
import com.codewithsaif.store.mappers.UserMapper;
import com.codewithsaif.store.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

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
            @RequestBody RegisterUserRequest request,
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
    private ResponseEntity<UserDto> updateUser(@PathVariable(name = "id") Long id,
                                               @RequestBody UpdateUserRequest request){
        var user = userRepository.findById(id).orElse(null);
        if(user == null)
            return ResponseEntity.notFound().build();
         userMapper.toUpdate(request,user);
         userRepository.save(user);

       return ResponseEntity.ok(userMapper.toDto(user));

    }

}
