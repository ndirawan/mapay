package com.enigma.mapay.service;

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
