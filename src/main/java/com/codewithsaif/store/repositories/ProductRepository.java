package com.codewithsaif.store.repositories;

import com.codewithsaif.store.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}