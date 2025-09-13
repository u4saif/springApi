package com.codewithsaif.store.dtos;

import com.codewithsaif.store.validations.LowerCaseValidation;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UpdateUserRequest {
    @NotBlank(message = "Name is required.")
    @Size(max=255,message = "Name should be less than 255 characters.")
    public String name;

    @NotBlank(message = "Email is required")
    @LowerCaseValidation(message = "Email must be lower case.")
    @Email
    public String email;
}
