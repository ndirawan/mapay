package com.enigma.mapay.controller;

import com.enigma.mapay.dto.UserDTO;
import com.enigma.mapay.dto.AuthRequest;
import com.enigma.mapay.dto.LoginDTO;
import com.enigma.mapay.service.AuthService;
import com.enigma.mapay.utils.customResponds.Response;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody AuthRequest authRequest){
        UserDTO register = authService.register(authRequest);
        Response<?> response = new Response<>("Successfuly create new user", register);

        return ResponseEntity.status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest authRequest){
        LoginDTO loginDTO = authService.login(authRequest);
        Response<?> response = new Response<>(
                "Successfully login", loginDTO
        );
        return ResponseEntity.ok(response);

    }
}
