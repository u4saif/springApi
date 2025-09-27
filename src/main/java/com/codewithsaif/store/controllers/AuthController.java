package com.codewithsaif.store.controllers;

import com.codewithsaif.store.dtos.JwtResponseDto;
import com.codewithsaif.store.dtos.UserLoginRequest;
import com.codewithsaif.store.services.JwtService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    @PostMapping("/login")
    public ResponseEntity<JwtResponseDto> userLogin(@Valid  @RequestBody UserLoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                request.getEmail(),request.getPassword())
        );

        var token = jwtService.generateToken(request.email);
        return ResponseEntity.ok(new JwtResponseDto(token));
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<Void> handleBadCredentialsException(){
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

}

