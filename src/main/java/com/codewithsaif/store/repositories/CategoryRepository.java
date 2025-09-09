package com.codewithsaif.store.repositories;

import com.codewithsaif.store.entities.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Byte> {
}