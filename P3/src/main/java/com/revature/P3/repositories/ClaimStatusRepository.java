package com.revature.P3.repositories;

import com.revature.P3.entities.ClaimStatus;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClaimStatusRepository extends CrudRepository<ClaimStatus, String> {
}
