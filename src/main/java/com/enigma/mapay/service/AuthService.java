package com.enigma.mapay.service;

import com.enigma.mapay.dto.AuthRequest;
import com.enigma.mapay.dto.LoginDTO;
import com.enigma.mapay.dto.UserDTO;

public interface AuthService {
    UserDTO register(AuthRequest user);

    LoginDTO login(AuthRequest user);
}
