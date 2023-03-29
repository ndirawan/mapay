package com.enigma.mapay.service.impl;

import com.enigma.mapay.entity.User;
import com.enigma.mapay.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserServiceImpl implements UserDetailsService {

    private final UserRepository appUserRepository;

    public CustomUserServiceImpl(UserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String phoneNumber) {
        Optional<User> appUser = appUserRepository.findByPhoneNumber(phoneNumber);
        if (!appUser.isPresent()) {
            try {
                throw new Exception("user not found");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return new UserDetailImpl(appUser.get());
    }

}
