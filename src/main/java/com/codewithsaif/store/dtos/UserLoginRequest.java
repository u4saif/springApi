package com.codewithsaif.store.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserLoginRequest {
    @NotBlank(message = "Username can not be empty")
    public String email;

    @NotBlank( message = "Password can not be empty")
    public String password;
}
