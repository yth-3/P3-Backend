package com.revature.P3.repositories;

import com.revature.P3.entities.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, String> {
    @Query(value = "SELECT * FROM users WHERE user_id = ?1", nativeQuery = true)
    User findByUserId(String userId);

    User findAllByUsername(String username);

    User findByEmail(String email);

    @Modifying
    @Query(value = "UPDATE users SET is_active = true WHERE user_id = ?1", nativeQuery = true)
    void activateUser(String userId);

    @Modifying
    @Query(value = "UPDATE users SET is_active = false WHERE user_id = ?1", nativeQuery = true)
    void deactivateUser(String userId);

    @Query(value = "SELECT * FROM users WHERE role_id = '0'", nativeQuery = true)
    List<User> findAllPatients();

    @Modifying
    @Query(value = "UPDATE users SET username = ?2 WHERE user_id = ?1", nativeQuery = true)
    void updateUsernameById(String userId, String username);
}
