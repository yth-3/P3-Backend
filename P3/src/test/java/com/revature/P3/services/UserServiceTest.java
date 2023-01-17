package com.revature.P3.services;

import com.revature.P3.dtos.requests.NewLoginRequest;
import com.revature.P3.dtos.requests.NewUserRequest;
import com.revature.P3.dtos.responses.Principal;
import com.revature.P3.entities.Role;
import com.revature.P3.entities.User;
import com.revature.P3.repositories.UserRepository;
import com.revature.P3.utils.custom_exceptions.BadGatewayException;
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
        catch (BadGatewayException exception) {
            test = true;
            assertTrue(exception.getMessage().equals("Bad Gateway; Try Again Later"));
        }

        assertTrue(test);
    }

    @Test
    public void test_loginThrowsErrorAssertThrows_login() {
        req.setPassword("randomPassword");
        Mockito.when(mockUserRepo.findAllByUsername(req.getUsername())).thenThrow(RuntimeException.class);

        BadGatewayException e = assertThrows(BadGatewayException.class, () -> {
            User u = sut.loginUser(req);
        });

        assertTrue(e.getMessage().equals("Bad Gateway; Try Again Later"));
    }

    @Test
    public void test_createUserThrowsErrorsProperly_createUser() {
        doThrow(RuntimeException.class).when(mockUserRepo).save(any());

        BadGatewayException e = assertThrows(BadGatewayException.class, () -> {
            sut.createPatient(nuReq);
        });

//        assertTrue(e.getMessage().equals("User cannot be created"));
    }

    @Test
    public void test_createUserThrowsErrorsProperly_createPatient() {
        doThrow(RuntimeException.class).when(mockUserRepo).save(any());

        assertThrows(BadGatewayException.class, () -> {
            sut.createPatient(nuReq);
        });
    }

    @Test
    public void test_createUserThrowsErrorsProperly_createNurse() {
        doThrow(RuntimeException.class).when(mockUserRepo).save(any());

        assertThrows(BadGatewayException.class, () -> {
            sut.createNurse(nuReq);
        });
    }

    @Test
    public void test_createUserThrowsErrorsProperly_createDoctor() {
        doThrow(RuntimeException.class).when(mockUserRepo).save(any());

        assertThrows(BadGatewayException.class, () -> {
            sut.createDoctor(nuReq);
        });
    }

    @Test
    public void test_createUserThrowsErrorsProperly_createInsurer() {
        doThrow(RuntimeException.class).when(mockUserRepo).save(any());

        assertThrows(BadGatewayException.class, () -> {
            sut.createInsurer(nuReq);
        });
    }

    @Test
    public void test_createUserThrowsErrorsProperly_createStaff() {
        doThrow(RuntimeException.class).when(mockUserRepo).save(any());

        assertThrows(BadGatewayException.class, () -> {
            sut.createStaff(nuReq);
        });
    }

    @Test
    public void test_getAllUsers_givenNothing() {
        // Arrange
        UserService spySut = Mockito.spy(sut);

        // Act
        spySut.getAllUsers();

        // Assert
        Mockito.verify(mockUserRepo, Mockito.times(1)).findAll();
    }

    @Test
    public void test_activateUser_givenUserId() {
        // Arrange
        String userId = user.getUserId();
        UserService spySut = Mockito.spy(sut);

        // Act
        spySut.activateUser(userId);

        // Assert
        Mockito.verify(mockUserRepo, Mockito.times(1)).activateUser(userId);
    }

    @Test
    public void test_deactivateUser_givenUserId() {
        // Arrange
        String userId = user.getUserId();
        UserService spySut = Mockito.spy(sut);

        // Act
        spySut.deactivateUser(userId);

        // Assert
        Mockito.verify(mockUserRepo, Mockito.times(1)).deactivateUser(userId);
    }

    @Test
    public void test_activateUserThrowsErrorsProperly_activateUser() {
        // Arrange
        String userId = user.getUserId();

        doThrow(RuntimeException.class).when(mockUserRepo).activateUser(any());

        // Assert
        InvalidUserException e = assertThrows(InvalidUserException.class, () -> {
            sut.activateUser(userId);
        });
    }

    @Test
    public void test_deactivateUserThrowsErrorsProperly_deactivateUser() {
        // Arrange
        String userId = user.getUserId();

        doThrow(RuntimeException.class).when(mockUserRepo).deactivateUser(any());

        // Assert
        InvalidUserException e = assertThrows(InvalidUserException.class, () -> {
            sut.deactivateUser(userId);
        });
    }

    @Test
    public void test_getNullUser_givenUserId() {
        // Arrange
        UserService spySut = Mockito.spy(sut);
        String userId = user.getUserId();

        Mockito.doReturn(null).when(mockUserRepo).findAllByUsername(userId);

        // Assert
        assertThrows(InvalidUserException.class, () -> {
            spySut.getUser(userId);
        });
    }

    @Test
    public void test_getUser_givenUserId() {
        // Arrange
        UserService spySut = Mockito.spy(sut);
        String userId = user.getUserId();

        Mockito.doReturn(user).when(mockUserRepo).findAllByUsername(userId);

        // Act
        Principal principal = spySut.getUser(userId);

        // Assert
        Mockito.verify(mockUserRepo, Mockito.times(1)).findAllByUsername(userId);

        assertEquals(user.getUserId(),principal.getUserId());
        assertEquals(user.getUsername(),principal.getUsername());
        assertEquals(user.getEmail(),principal.getEmail());
        assertEquals(user.getRegistered().toString(),principal.getRegistered());
        assertEquals(user.getActive(),principal.isActive());
        assertEquals(user.getRole().getRole(),principal.getRole());
        assertEquals(null,principal.getToken());
    }

    @Test
    public void test_getAllPatients_givenNothing() {
        // Arrange
        UserService spySut = Mockito.spy(sut);

        // Act
        spySut.getAllPatients();

        // Assert
        Mockito.verify(mockUserRepo, Mockito.times(1)).findAllPatients();
    }
}
