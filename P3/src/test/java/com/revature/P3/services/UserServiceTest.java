package com.revature.P3.services;

import com.revature.P3.dtos.requests.NewLoginRequest;
import com.revature.P3.dtos.responses.Principal;
import com.revature.P3.entities.Role;
import com.revature.P3.entities.User;
import com.revature.P3.repositories.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.sql.Timestamp;

import static org.junit.Assert.*;

public class UserServiceTest {
    private UserService sut;
    private NewLoginRequest req;
    private String password;
    private User user;

    private final UserRepository mockUserRepo = Mockito.mock(UserRepository.class);

    @Before
    public void init() {
        sut = new UserService(mockUserRepo);
        password = "passw0rd";
        req = new NewLoginRequest("username", password);

        user = new User("randomID",
                "username",
                        HashService.getHash(password),
                   "email@email.net",
                        new Timestamp(0),
                true,
                        new Role());
    }

    @Test
    public void test_simpleValidLogin_login() {
        Mockito.when(mockUserRepo.findAllByUsername(req.getUsername())).thenReturn(user);

        User u = sut.loginUser(req);

        assertFalse(u == null);
        assertTrue(HashService.verify(u.getPassword(), req.getPassword()));
    }
}
