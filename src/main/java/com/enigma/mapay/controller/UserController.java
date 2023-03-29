package com.enigma.mapay.controller;

import com.enigma.mapay.entity.User;
import com.enigma.mapay.service.UserService;
import com.enigma.mapay.utils.constant.ApiUrlConstant;
import com.enigma.mapay.utils.exception.DataNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(ApiUrlConstant.USER_PATH)
@AllArgsConstructor
public class UserController {
    UserService userService;
    private PasswordEncoder passwordEncoder;
    @PostMapping
    public ResponseEntity<User> saveUser(@RequestBody User user) throws IOException {
        return ResponseEntity.ok(userService.saveUser(user));
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable String id){
        return userService.getUserById(id);
    }

    @GetMapping
    public List<User> getUsers(){
        return userService.getAllUser();
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable String id,@RequestBody User user){
        Optional<User> existingUser = Optional.ofNullable(userService.getUserById(id));
        if (existingUser.isPresent()) {
            User user1 = existingUser.get();
            if (user.getPhoneNumber() != null) {
                user1.setPhoneNumber(user.getPhoneNumber());
            }
            if (user.getAddress() != null) {
                user1.setAddress(user.getAddress());
            }
            if (user.getEmail() != null) {
                user1.setEmail(user.getEmail());
            }
            if (user.getFullName() != null) {
                user1.setFullName(user.getFullName());
            }
            if (user.getBirthDate() != null) {
                user1.setBirthDate(user.getBirthDate());
            }
            if (user.getStatus() != null) {
                user1.setStatus(user.getStatus());
            }
            if (user.getPassword() != null) {
                user1.setPassword(passwordEncoder.encode(user.getPassword()));
            }
            if (user.getRoles() != null) {
                user1.setRoles(user.getRoles());
            }

            return userService.updateUser(user1);
        } else {
            throw new DataNotFoundException("User not found with id " + id);
        }
    }

    @DeleteMapping("/{id}")
    void deleteUserById(@PathVariable String id){
        userService.deleteUser(id);
    }
}
