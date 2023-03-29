package com.enigma.mapay.dto;

import com.enigma.mapay.entity.User;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class UserDTO {
    private String email;
    private String phoneNumber;

    private String fullName;
    private String address;
    private Date birthDate;
    private Integer status;
    private List<String> roles;

    public UserDTO(User user) {
        this.email = user.getEmail();
        this.phoneNumber = user.getPhoneNumber();
        this.fullName = user.getFullName();
        this.address = user.getAddress();
        this.birthDate = user.getBirthDate();
        this.status = user.getStatus();
        this.roles = user.getRoles().stream().map(role -> role.getRole().name())
                .collect(Collectors.toList());
    }
}
