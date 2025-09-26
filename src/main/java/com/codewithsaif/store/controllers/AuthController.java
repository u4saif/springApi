package com.codewithsaif.store.controllers;

import com.codewithsaif.store.dtos.UserLoginRequest;
import com.codewithsaif.store.repositories.UserRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@AllArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<?> userLogin(@Valid  @RequestBody UserLoginRequest request) {

        var user = userRepository.findByEmail(request.email).orElse(null);
        if (user == null) {
            var errorMessage = Map.of("error", "Invalid username / password");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorMessage);
        }
        if(!passwordEncoder.matches(request.getPassword(), user.getPassword())){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        };
        return ResponseEntity.ok().build();
    }
}

