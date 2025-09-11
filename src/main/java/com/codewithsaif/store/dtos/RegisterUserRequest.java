package com.codewithsaif.store.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class RegisterUserRequest {
    public String name;
    public String email;
    public String password;
}
