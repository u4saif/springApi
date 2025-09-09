package com.codewithsaif.store.mappers;

import com.codewithsaif.store.dtos.UserDto;
import com.codewithsaif.store.entities.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
     UserDto toDto(User user);
}
