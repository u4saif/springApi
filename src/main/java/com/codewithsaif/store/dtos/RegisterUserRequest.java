package com.codewithsaif.store.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class RegisterUserRequest {
    @NotBlank(message = "Name is required.")
    @Size(max=255,message = "Name should be less than 255 characters.")
    public String name;

    @NotBlank(message = "Email is required")
    @Email
    public String email;

    @NotBlank(message = "Password is required")
    @Size(min = 8,message = "Password should be greater than 8 character")
    public String password;
}
