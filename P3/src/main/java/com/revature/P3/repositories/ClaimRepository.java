package com.revature.P3.repositories;

import com.revature.P3.entities.Claim;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClaimRepository extends CrudRepository<Claim, String> {
    @Query(value = "SELECT * FROM claims WHERE submitter_id = '0'", nativeQuery = true)
    List<Claim> findAllPatientClaims();

    @Query(value = "SELECT * FROM claims WHERE submitter_id = '1'", nativeQuery = true)
    List<Claim> findAllNurseClaims();

    @Query(value = "SELECT * FROM claims WHERE submitter_id = '2'", nativeQuery = true)
    List<Claim> findAllDoctorClaims();

    @Query(value = "SELECT * FROM claims WHERE submitter_id = '3'", nativeQuery = true)
    List<Claim> findAllInsurerClaims();

    @Query(value = "SELECT * FROM claims WHERE submitter_id = '5'", nativeQuery = true)
    List<Claim> findAllStaffClaims();
}
