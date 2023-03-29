package com.enigma.mapay.service;

import com.enigma.mapay.dto.AuthRequest;
import com.enigma.mapay.dto.LoginDTO;
import com.enigma.mapay.dto.UserDTO;
import com.enigma.mapay.entity.User;

import java.util.List;

public interface UserService {
    User saveUser(User user);
    User updateUser(User user);
    User getUserById(String id);
    User getUserByPhoneNumb(String phone);
    List<User> getAllUser();
    void deleteUser(String id);

}
