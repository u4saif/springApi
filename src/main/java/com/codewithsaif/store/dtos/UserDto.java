package com.codewithsaif.store.dtos;

import com.codewithsaif.store.validations.LowerCaseValidation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
public class UserDto {
    public Long id;
    public String name;
    public String email;
}
