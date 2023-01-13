package com.revature.P3.repositories;

import com.revature.P3.entities.Claim;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClaimRepository extends CrudRepository<Claim, String> {
    @Query(value = "SELECT * FROM claims AS A LEFT JOIN users AS B ON A.submitter_id = B.user_id WHERE B.role_id = '0'", nativeQuery = true)
    List<Claim> findAllPatientClaims();

    @Query(value = "SELECT * FROM claims AS A LEFT JOIN users AS B ON A.submitter_id = B.user_id WHERE B.role_id = '1'", nativeQuery = true)
    List<Claim> findAllNurseClaims();

    @Query(value = "SELECT * FROM claims AS A LEFT JOIN users AS B ON A.submitter_id = B.user_id WHERE B.role_id = '2'", nativeQuery = true)
    List<Claim> findAllDoctorClaims();

    @Query(value = "SELECT * FROM claims AS A LEFT JOIN users AS B ON A.submitter_id = B.user_id WHERE B.role_id = '3'", nativeQuery = true)
    List<Claim> findAllInsurerClaims();

    @Query(value = "SELECT * FROM claims AS A LEFT JOIN users AS B ON A.submitter_id = B.user_id WHERE B.role_id = '5'", nativeQuery = true)
    List<Claim> findAllStaffClaims();
}
