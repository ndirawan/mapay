package com.enigma.mapay.service.impl;

import com.enigma.mapay.dto.*;
import com.enigma.mapay.entity.Account;
import com.enigma.mapay.entity.Role;
import com.enigma.mapay.repository.AccountRepository;
import com.enigma.mapay.security.JwtUtils;
import com.enigma.mapay.service.AuthService;
import com.enigma.mapay.service.RoleService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;

@Service
@Slf4j
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private AccountRepository accountRepository;
    private AuthenticationManager authenticationManager;
    private PasswordEncoder passwordEncoder;
    private JwtUtils jwtUtils;
    private RoleService roleService;


    @Override
    public AccountDTO register(AuthRequest account) {
        Role role = roleService.getOrSave(ERole.ROLE_USER);
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        Account userSaved = accountRepository.save(new Account(null, account.getPhoneNumber(), account.getPassword(),
                new ArrayList<>(Collections.singleton(role))));
        return new AccountDTO(userSaved);
    }

    @Override
    public LoginDTO login(AuthRequest user) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        user.getPhoneNumber(),
                        user.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        AccountDetailsImpl accountDetails = (AccountDetailsImpl) authentication.getPrincipal();
        String token = jwtUtils.generateTokenFromUsername(accountDetails.getUsername());
        return new LoginDTO(accountDetails, token);
    }
}
