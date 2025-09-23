package com.codewithsaif.store.controllers;

import com.codewithsaif.store.dtos.AddItemToCartRequest;
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
import com.codewithsaif.store.services.CartService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

@AllArgsConstructor
@RestController
@Tag(name = "Cart")
@RequestMapping("/carts")
public class CartController {

    private final CartRepository cartRepository;
    private final CartMapper cartMapper;
    private final ProductRepository productRepository;
    private final CartService cartService;

    @GetMapping("/{cartId}")
    public ResponseEntity<CartDto> getCart(@PathVariable UUID cartId){
        var cartDto = cartService.getCart(cartId);
        return ResponseEntity.ok(cartDto);
    }

    @PostMapping
    public ResponseEntity<CartDto> createCart(){
        var cartDto = cartService.createCart();
        return new ResponseEntity<>(cartDto, HttpStatus.CREATED);
    }

    @PostMapping("/{cartId}/items")
    @Operation(summary = "Adds item to cart.")
    public ResponseEntity<?> addToCart(
            @Parameter( description = "The Id of cart")
            @PathVariable UUID cartId,
            @RequestBody AddItemToCartRequest request
    ){
        var carItemDto = cartService.addToCart(cartId,request.getProductId());
        return ResponseEntity.status(HttpStatus.CREATED).body(carItemDto);
    }

    @PutMapping("/{cartId}/items/{productId}")
    public ResponseEntity<?> updateCart(
            @PathVariable UUID cartId ,
            @PathVariable Long productId,
            @Valid @RequestBody UpdateCartRequest request
    ){
        var cartDto = cartService.updateCart(cartId, productId, request );
        return ResponseEntity.ok(cartDto);
    }

    @DeleteMapping("/{cartId}/items")
    public ResponseEntity<?> clearCart(
            @PathVariable("cartId") UUID cartId
    ){
        var cartDto = cartService.clearCart(cartId);
        return  ResponseEntity.ok(cartDto);
    }

    @DeleteMapping("/{cartId}/items/{productId}")
    public ResponseEntity<?> deleteProduct(
            @PathVariable("cartId") UUID cartId ,
            @PathVariable("productId") Long productId
    ){
        cartService.deleteProduct(cartId,productId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }


    @ExceptionHandler(CartNotFoundException.class)
    public ResponseEntity<Map<String,String>> handleCartNotFoundException(){
        var errorMessage = Map.of("error","Invalid cart provided.");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<Map<String,String>> handleProductNotFoundException(){
        var errorMessage = Map.of("error","Product not found.");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
    }
}
