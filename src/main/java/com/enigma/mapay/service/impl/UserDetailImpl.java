package com.enigma.mapay.service.impl;

import com.enigma.mapay.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
public class UserDetailImpl implements UserDetails {
    private String id;
    private String phoneNumber;
    private String password;
    private String email;
    private String fullName;
    private String address;
    private Date birthDate;
    private Integer status;


    public Collection<? extends GrantedAuthority> authorities;

        public UserDetailImpl(User appUser) {
            List<SimpleGrantedAuthority> roles = appUser.getRoles().stream()
                    .map(role -> new SimpleGrantedAuthority(role.getRole().name()))
                    .collect(Collectors.toList());

            this.id = appUser.getId();
            this.phoneNumber = appUser.getPhoneNumber();
            this.password = appUser.getPassword();
            this.authorities = roles;
            this.email  = appUser.getEmail();
            this.fullName = appUser.getFullName();
            this.address = appUser.getAddress();
            this.birthDate = appUser.getBirthDate();
            this.status = appUser.getStatus();
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
