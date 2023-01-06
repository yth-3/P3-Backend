package com.revature.P3.repositories;

import com.revature.P3.entities.ClaimType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClaimTypeRepository extends CrudRepository<ClaimType, String> {
}
