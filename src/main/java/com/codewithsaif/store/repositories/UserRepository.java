package com.codewithsaif.store.repositories;

import com.codewithsaif.store.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
