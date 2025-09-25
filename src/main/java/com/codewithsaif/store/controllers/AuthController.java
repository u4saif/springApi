package com.codewithsaif.store.controllers;

import com.codewithsaif.store.dtos.UserLoginRequest;
import com.codewithsaif.store.repositories.UserRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserRepository userRepository;

    public AuthController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/login")
    public ResponseEntity<?> userLogin(@Valid  @RequestBody UserLoginRequest request) {

        var userExist = userRepository.existsByEmail(request.email);
        if (!userExist) {
            var errorMessage = Map.of("error", "Invalid username / password");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
        }
        var loginMessage = Map.of("message", "Token is #$2a$10$CwLjF.BqxvcR");
        return ResponseEntity.ok(loginMessage);
    }
}

