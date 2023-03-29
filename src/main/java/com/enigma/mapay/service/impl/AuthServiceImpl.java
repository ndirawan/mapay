package com.enigma.mapay.service.impl;

import com.enigma.mapay.dto.*;
import com.enigma.mapay.entity.Role;
import com.enigma.mapay.entity.User;
import com.enigma.mapay.repository.UserRepository;
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

    private UserRepository userRepository;
    private AuthenticationManager authenticationManager;
    private PasswordEncoder passwordEncoder;
    private JwtUtils jwtUtils;
    private RoleService roleService;



    @Override
    public UserDTO register(AuthRequest user) {
        Role role = roleService.getOrSave(ERole.ROLE_USER);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User userSaved = userRepository.save(new User(null,user.getEmail(),user.getPhoneNumber(),user.getFullName(),user.getAddress(),user.getBirthDate(),user.getStatus(),user.getPassword(),new ArrayList<>(Collections.singleton(role)),user.getBalance(),user.getMapay_point()));
        return new UserDTO(userSaved);
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
        UserDetailImpl userDetail = (UserDetailImpl) authentication.getPrincipal();
        String token = jwtUtils.generateTokenFromUsername(userDetail.getUsername());
        return new LoginDTO(userDetail, token);
    }
}
