package com.enigma.mapay.controller;


import com.enigma.mapay.entity.User;
import com.enigma.mapay.service.UserService;
import com.enigma.mapay.utils.constant.ApiUrlConstant;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.jws.soap.SOAPBinding;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(ApiUrlConstant.USER_PATH)
@AllArgsConstructor
public class UserController {
    UserService userService;

    private static final String UPLOAD_DIR = "./src/main/resources/profile";

    @PostMapping
    public ResponseEntity<User> saveUser(@RequestParam("fullName") String name,
                                           @RequestParam("email") String email,
                                           @RequestParam("phoneNumb") String phone,
                                           @RequestParam("address") String address,
                                           @RequestParam("status") Integer status,
                                           @RequestParam("birthDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate birthdate,
                                           @RequestParam(value = "profilePicture", required = false) MultipartFile photo)throws IOException {

        User user = new User();
        user.setEmail(email);
        user.setPhoneNumb(phone);
        user.setFullName(name);
        user.setAddress(address);
        user.setBirthDate(birthdate);
        user.setStatus(status);
        if (photo != null && !photo.isEmpty()) {
            String fileName = StringUtils.cleanPath(photo.getOriginalFilename());
            Path uploadPath = Paths.get(UPLOAD_DIR);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(photo.getInputStream(), filePath);
            user.setProfilePicture(fileName);
        }
        user.setAddress(address);
        userService.saveUser(user);
        return ResponseEntity.ok(user);
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
