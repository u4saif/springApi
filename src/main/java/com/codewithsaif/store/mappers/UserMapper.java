package com.codewithsaif.store.mappers;

import com.codewithsaif.store.dtos.RegisterUserRequest;
import com.codewithsaif.store.dtos.UpdateUserRequest;
import com.codewithsaif.store.dtos.UserDto;
import com.codewithsaif.store.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
     UserDto toDto(User user);
     User toEntity(RegisterUserRequest user);
     User toUpdate(UpdateUserRequest request, @MappingTarget User user);
}
