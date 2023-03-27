package com.enigma.mapay.service;

import com.enigma.mapay.dto.AuthRequest;
import com.enigma.mapay.dto.LoginDTO;
import com.enigma.mapay.dto.AccountDTO;
import com.enigma.mapay.entity.User;

public interface AuthService {
    AccountDTO register(AuthRequest account);

    LoginDTO login(AuthRequest user);
}
