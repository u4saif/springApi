package com.codewithsaif.store.repositories;

import com.codewithsaif.store.entities.Profile;
import org.springframework.data.repository.CrudRepository;

public interface ProfileRepository extends CrudRepository<Profile, Long> {
}