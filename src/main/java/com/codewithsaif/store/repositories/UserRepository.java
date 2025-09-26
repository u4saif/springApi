package com.codewithsaif.store.repositories;

import com.codewithsaif.store.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
//    @Query("select (count(u) > 0) from User u where u.email = ?1")
    boolean existsByEmail(String email);
    Optional<User> findByEmail(String email);
}
