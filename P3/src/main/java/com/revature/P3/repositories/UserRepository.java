package com.revature.P3.repositories;

import com.revature.P3.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, String> {
    User findAllByUsername(String username);
}
