package com.codewithsaif.store.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ChangePasswordRequest {
    public String oldPassword;

    @NotBlank(message = "Password is required")
    @Size(min = 8,message = "Password should be greater than 8 character")
    public String newPassword;
}
