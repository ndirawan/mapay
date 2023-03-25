package com.enigma.mapay.controller;

import com.enigma.mapay.entity.User;
import com.enigma.mapay.service.UserService;
import com.enigma.mapay.utils.constant.ApiUrlConstant;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.jws.soap.SOAPBinding;
import java.util.List;

@RestController
@RequestMapping(ApiUrlConstant.USER_PATH)
@AllArgsConstructor
public class UserController {
    UserService userService;

    @PostMapping
    public User saveUser(@RequestBody User user){
        return userService.saveUser(user);
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable String id){
        return userService.getUserById(id);
    }

    @GetMapping
    public List<User> getUsers(){
        return userService.getAllUser();
    }

    @PutMapping
    public User updateUser(@RequestBody User user){
        return userService.updateUser(user);
    }

    @DeleteMapping("/{id}")
    void deleteUserById(@PathVariable String id){
        userService.deleteUser(id);
    }
}
