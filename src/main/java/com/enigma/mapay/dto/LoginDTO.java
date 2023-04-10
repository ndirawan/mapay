package com.enigma.mapay.dto;

import com.enigma.mapay.service.impl.UserDetailImpl;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class LoginDTO {
    private String phoneNumber;
    @JsonIgnore
    private List<String> roles;
    private String token;

    public LoginDTO(UserDetailImpl user, String token) {
        this.phoneNumber = user.getUsername();
        this.roles = user.getAuthorities().stream().map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        this.token = token;
    }
}
