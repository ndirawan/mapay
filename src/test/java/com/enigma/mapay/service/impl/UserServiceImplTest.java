package com.enigma.mapay.service.impl;

import com.enigma.mapay.entity.User;
import com.enigma.mapay.repository.UserRepository;
import com.enigma.mapay.utils.exception.DataNotFoundException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {
    private User user;
    @BeforeEach
    void setup() {
        user = new User("01", "tika@gmail.com", "082165417356", "tika", "jakarta", Date.valueOf("1999-10-10"),null,1,0,0);
    }

    @AfterEach
    void cleanUp(){
        user = new User();
    }

    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserServiceImpl userService;

    @Test
    void saveUser() {
        User user = new User("01", "tika@gmail.com", "082165417356", "tika", "jakarta", Date.valueOf("1999-10-10"),null,1,0,0);
        when(userRepository.save(user)).thenReturn(user);
        User savedUser = userService.saveUser(user);
        assertNotNull(savedUser);
        assertEquals(user.getId(),savedUser.getId());
        assertEquals(user.getAddress(),savedUser.getAddress());

        verify(userRepository,times(1)).save(user);
    }

    @Test
    void updateUser() {
        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
        when(userRepository.save(user)).thenReturn(user);

        User updateUser = userService.updateUser(user);

        assertNotNull(updateUser);
        assertEquals(user.getId(), updateUser.getId());
        assertEquals(user.getFullName(), updateUser.getFullName());

        verify(userRepository, times(1)).findById(user.getId());
        verify(userRepository,times(1)).save(user);
    }

    @Test
    void updateProductByIdNotPresent() {
        when(userRepository.findById(user.getId())).thenReturn(Optional.empty());

        Throwable example = assertThrows(DataNotFoundException.class, ()->{
            userService.updateUser(user);
        });

        assertEquals("Not Found id : 01", example.getMessage());

        verify(userRepository, times(1)).findById(user.getId());
        verify(userRepository, never()).save(user);
    }

    @Test
    void getUserById() {
        when(userRepository.findById("01")).thenReturn(Optional.of(Optional.of(user).get()));
        User user = userService.getUserById("01");
        assertEquals("tika", user.getFullName());
        assertEquals("tika@gmail.com", user.getEmail());
        verify(userRepository, times(1)).findById("01");
    }

    @Test
    void getAllUser() {
        List<User> listOfUser = new ArrayList<>();
        User user = new User("01", "tika@gmail.com", "082165417356", "tika", "jakarta", Date.valueOf("1999-10-10"),null,1,0,0);
        User user1 = new User("01", "tika@gmail.com", "082165417356", "tika", "jakarta", Date.valueOf("1999-10-10"),null,1,0,0);
        listOfUser.add(user);
        listOfUser.add(user1);

        when(userRepository.findAll()).thenReturn(listOfUser);

        List<User> userList = userService.getAllUser();
        assertEquals(2, userList.size());

        verify(userRepository, times(1)).findAll();
    }

    @Test
    void deleteUser() {
        String id = "01";
        userService.deleteUser(id);
        verify(userRepository, times(1)).deleteById(id);
    }
}