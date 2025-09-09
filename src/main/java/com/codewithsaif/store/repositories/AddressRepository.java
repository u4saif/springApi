package com.codewithsaif.store.repositories;

import com.codewithsaif.store.entities.Address;
import org.springframework.data.repository.CrudRepository;

public interface AddressRepository extends CrudRepository<Address, Long> {
}