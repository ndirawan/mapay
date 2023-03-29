package com.enigma.mapay.service.impl;

import com.enigma.mapay.entity.Account;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Setter
@NoArgsConstructor
public class AccountDetailsImpl implements UserDetails {

    private String id;
    private String phoneNumber;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

        public AccountDetailsImpl(Account appUser) {
            List<SimpleGrantedAuthority> roles = appUser.getRoles().stream()
                    .map(role -> new SimpleGrantedAuthority(role.getRole().name()))
                    .collect(Collectors.toList());

        this.id = appUser.getId();
        this.phoneNumber = appUser.getPhoneNumber();
        this.password = appUser.getPassword();
        this.authorities = roles;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return phoneNumber;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
