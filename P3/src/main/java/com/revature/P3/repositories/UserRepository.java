package com.revature.P3.repositories;

import com.revature.P3.entities.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, String> {
    User findAllByUsername(String username);

    @Modifying
    @Query("UPDATE users SET is_active = true WHERE user_id = ?1")
    void activateUser(String userId);

    @Modifying
    @Query("UPDATE users SET is_active = false WHERE user_id = ?1")
    void deactivateUser(String userId);
}
