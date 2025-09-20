package com.codewithsaif.store.mappers;

import com.codewithsaif.store.dtos.CartDto;
import com.codewithsaif.store.dtos.CartItemDto;
import com.codewithsaif.store.entities.Cart;
import com.codewithsaif.store.entities.CartItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CartMapper {
    @Mapping(target = "items", source = "cartItems")
    @Mapping(target = "totalPrice", expression="java(cart.getTotalPrice())")
    CartDto toDto(Cart cart);

    @Mapping(target = "totalPrice", expression="java(cartItem.getTotalPrice())")
    CartItemDto toDto(CartItem cartItem);
}
