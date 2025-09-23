package com.codewithsaif.store.services;

import com.codewithsaif.store.dtos.CartDto;
import com.codewithsaif.store.dtos.CartItemDto;
import com.codewithsaif.store.dtos.UpdateCartRequest;
import com.codewithsaif.store.entities.Cart;
import com.codewithsaif.store.entities.CartItem;
import com.codewithsaif.store.exceptions.CartNotFoundException;
import com.codewithsaif.store.exceptions.ProductNotFoundException;
import com.codewithsaif.store.mappers.CartMapper;
import com.codewithsaif.store.repositories.CartRepository;
import com.codewithsaif.store.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;

@AllArgsConstructor
@Service
public class CartService {

    private final CartRepository cartRepository;
    private final CartMapper cartMapper;
    private final ProductRepository productRepository;

    public CartDto createCart(){
        var cart = new Cart();
        cartRepository.save(cart);
        return cartMapper.toDto(cart);
    }

    public CartDto getCart(UUID cartId){
        var cart = cartRepository.findById(cartId).orElse(null);
        if(cart == null){
            throw new CartNotFoundException();
        }
        return  cartMapper.toDto(cart);
    }

    public CartItemDto addToCart(UUID cartId , Long productId){
        var cart = cartRepository.findById(cartId).orElse(null);
        if(cart == null){
            throw new CartNotFoundException();
        }

        var product = productRepository.findById(productId).orElse(null);
        if(product == null){
            throw new ProductNotFoundException();
        }

        var cartItem = cart.getCartItems().stream()
                .filter(item -> item.getProduct().getId().equals(product.getId()))
                .findFirst()
                .orElse(null);

        if(cartItem != null){
            cartItem.setQuantity(cartItem.getQuantity()+1);
        } else {
            cartItem = new CartItem();
            cartItem.setProduct(product);
            cartItem.setCart(cart);
            cart.getCartItems().add(cartItem);
        }

        cartRepository.save(cart);
        return cartMapper.toDto(cartItem);
    }

    public CartDto updateCart(UUID cartId, Long productId, UpdateCartRequest request){
        var cart = cartRepository.findById(cartId).orElse(null);
        if(cart == null){
            throw new CartNotFoundException();
        }

        var product = productRepository.findById(productId).orElse(null);
        if(product == null){
            throw new ProductNotFoundException();
        }

        var cartItem = cart.getItem(productId);
        if(cartItem == null){
            throw new ProductNotFoundException();
        } else {
            cartItem.setQuantity(request.getQuantity());
        }

        cartRepository.save(cart);
        return cartMapper.toDto(cart);
    }

    public CartDto clearCart(UUID cartId){
        var cart = cartRepository.findById(cartId).orElse(null);
        if(cart == null){
            throw new CartNotFoundException();
        }
        cart.clearCart();
        cartRepository.save(cart);
        return cartMapper.toDto(cart);
    }

    public void deleteProduct(UUID cartId, Long productId){
        var cart = cartRepository.findById(cartId).orElse(null);
        if(cart == null){
            throw new CartNotFoundException();
        }
        cart.removeProduct(productId);
        cartRepository.save(cart);
    }
}
