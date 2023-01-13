package com.revature.P3.repositories;

import com.revature.P3.entities.Claim;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface ClaimRepository extends CrudRepository<Claim, String> {
    @Query(value = "SELECT * FROM claims WHERE submitter_id = ?1", nativeQuery = true)
    List<Claim> findAllByUserId(String userId);

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

    @Modifying
    @Query(value = "UPDATE claims SET resolver_id = ?2 WHERE claim_id = ?1", nativeQuery = true)
    void setResolverId(String claimId, String resolverId);

    @Modifying
    @Query(value = "UPDATE claims SET resolved = ?2 WHERE claim_id = ?1", nativeQuery = true)
    void setResolved(String claimId, Timestamp resolved);

    @Modifying
    @Query(value = "UPDATE claims SET settled = ?2 WHERE claim_id = ?1", nativeQuery = true)
    void setSettled(String claimId, Double settled);

    @Modifying
    @Query(value = "UPDATE claims SET status_id = ?2 WHERE claim_id = ?1", nativeQuery = true)
    void setStatus(String claimId, String status);
}
