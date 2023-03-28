package com.enigma.mapay.service.impl;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountDetailsImplTest {

    @Test
    void getAuthorities() {
    }

    @Test
    void getPassword() {
        AccountDetailsImpl test = new AccountDetailsImpl();
        test.setPassword("123");
        assertTrue(test.getPassword() == "123");
    }

    @Test
    void getUsername() {
        AccountDetailsImpl test = new AccountDetailsImpl();
        test.setPhoneNumber("081231409182");
        assertTrue(test.getUsername() == "081231409182");
    }

    @Test
    void isAccountNonExpired() {
        AccountDetailsImpl test = new AccountDetailsImpl();
        assertTrue(test.isAccountNonExpired() == true);
    }

    @Test
    void isAccountNonLocked() {
        AccountDetailsImpl test = new AccountDetailsImpl();
        assertTrue(test.isAccountNonLocked() == true);
    }

    @Test
    void isCredentialsNonExpired() {
        AccountDetailsImpl test = new AccountDetailsImpl();
        assertTrue(test.isCredentialsNonExpired() == true);
    }

    @Test
    void isEnabled() {
        AccountDetailsImpl test = new AccountDetailsImpl();
        assertTrue(test.isEnabled() == true);
    }
}