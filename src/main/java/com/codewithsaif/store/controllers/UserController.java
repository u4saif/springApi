package com.codewithsaif.store.controllers;

import com.codewithsaif.store.Dtos.UserDto;
import com.codewithsaif.store.entities.User;
import com.codewithsaif.store.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserRepository userRepository;

    @GetMapping
    private List<UserDto> getUsers(){
        return userRepository.findAll().stream()
                .map(user-> new UserDto(user.getId(),user.getName(),user.getEmail()))
                .toList();
    }

    @GetMapping("/{id}")
    private ResponseEntity<UserDto> getUser(@PathVariable Long id){
        var user =  userRepository.findById(id).orElse(null);
        if(user == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(new UserDto(user.getId(),user.getName(),user.getEmail()));
    }

}
