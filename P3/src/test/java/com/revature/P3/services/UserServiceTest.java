package com.revature.P3.services;

import com.revature.P3.dtos.requests.NewLoginRequest;
import com.revature.P3.dtos.requests.NewUserRequest;
import com.revature.P3.entities.Role;
import com.revature.P3.entities.User;
import com.revature.P3.repositories.UserRepository;
import com.revature.P3.utils.custom_exceptions.InvalidAuthException;
import com.revature.P3.utils.custom_exceptions.InvalidUserException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.sql.Timestamp;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;

public class UserServiceTest {
    private UserService sut;
    private NewLoginRequest req;
    private String password;
    private User user;
    private NewUserRequest nuReq;

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

        nuReq = new NewUserRequest("tester", "passw0rd", "example@example.com");
    }

    @Test
    public void test_simpleValidLogin_login() {
        Mockito.when(mockUserRepo.findAllByUsername(req.getUsername())).thenReturn(user);

        User u = sut.loginUser(req);

        assertFalse(u == null);
    }

    @Test
    public void test_loginThrowsError_login() {
        req.setPassword("randomPassword");
        Mockito.when(mockUserRepo.findAllByUsername(req.getUsername())).thenThrow(RuntimeException.class);

        boolean test = false;
        try {
            User u = sut.loginUser(req);
        }
        catch (InvalidAuthException exception) {
            test = true;
            assertTrue(exception.getMessage().equals("Not Authorized"));
        }

        assertTrue(test);
    }

    @Test
    public void test_loginThrowsErrorAssertThrows_login() {
        req.setPassword("randomPassword");
        Mockito.when(mockUserRepo.findAllByUsername(req.getUsername())).thenThrow(RuntimeException.class);

        InvalidAuthException e = assertThrows(InvalidAuthException.class, () -> {
            User u = sut.loginUser(req);
        });

        assertTrue(e.getMessage().equals("Not Authorized"));
    }

    @Test
    public void test_createUserThrowsErrorsProperly_createUser() {
        doThrow(RuntimeException.class).when(mockUserRepo).save(any());

        InvalidUserException e = assertThrows(InvalidUserException.class, () -> {
            sut.createPatient(nuReq);
        });

//        assertTrue(e.getMessage().equals("User cannot be created"));
    }

    @Test
    public void test_createUserThrowsErrorsProperly_createPatient() {
        doThrow(RuntimeException.class).when(mockUserRepo).save(any());

        InvalidUserException e = assertThrows(InvalidUserException.class, () -> {
            sut.createPatient(nuReq);
        });
    }

    @Test
    public void test_createUserThrowsErrorsProperly_createNurse() {
        doThrow(RuntimeException.class).when(mockUserRepo).save(any());

        InvalidUserException e = assertThrows(InvalidUserException.class, () -> {
            sut.createNurse(nuReq);
        });
    }

    @Test
    public void test_createUserThrowsErrorsProperly_createDcotor() {
        doThrow(RuntimeException.class).when(mockUserRepo).save(any());

        InvalidUserException e = assertThrows(InvalidUserException.class, () -> {
            sut.createDoctor(nuReq);
        });
    }

    @Test
    public void test_createUserThrowsErrorsProperly_createInsurer() {
        doThrow(RuntimeException.class).when(mockUserRepo).save(any());

        InvalidUserException e = assertThrows(InvalidUserException.class, () -> {
            sut.createInsurer(nuReq);
        });
    }

    @Test
    public void test_createUserThrowsErrorsProperly_createStaff() {
        doThrow(RuntimeException.class).when(mockUserRepo).save(any());

        InvalidUserException e = assertThrows(InvalidUserException.class, () -> {
            sut.createStaff(nuReq);
        });
    }
}
