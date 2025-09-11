package com.codewithsaif.store.repositories;

import com.codewithsaif.store.entities.Product;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
     List<Product> findByCategoryId(Byte id);

     @EntityGraph(attributePaths = "category")
     @Query("SELECT p FROM Product p")
     List<Product> findWithCategory();
}