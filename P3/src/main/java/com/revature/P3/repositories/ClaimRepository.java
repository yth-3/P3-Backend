package com.revature.P3.repositories;

import com.revature.P3.entities.Claim;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClaimRepository extends CrudRepository<Claim, String> {
}
