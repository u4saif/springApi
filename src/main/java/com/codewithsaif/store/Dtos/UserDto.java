package com.codewithsaif.store.Dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
@AllArgsConstructor
@Data
public class UserDto {
    private Long id;
    private String name;
    private String email;
}
