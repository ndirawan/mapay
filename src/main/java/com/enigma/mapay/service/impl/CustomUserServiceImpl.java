package com.enigma.mapay.service.impl;

import com.enigma.mapay.entity.Account;
import com.enigma.mapay.repository.AccountRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserServiceImpl implements UserDetailsService {

    private final AccountRepository appUserRepository;

    public CustomUserServiceImpl(AccountRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        Optional<Account> appUser = appUserRepository.findByPhoneNumber(username);
        if (!appUser.isPresent()) {
            try {
                throw new Exception("user not found");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return new AccountDetailsImpl(appUser.get());
    }

}
