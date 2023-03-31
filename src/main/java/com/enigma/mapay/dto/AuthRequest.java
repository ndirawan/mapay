package com.enigma.mapay.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AuthRequest {
    private String email;
    private String phoneNumber;
    private String fullName;
    private String address;
    private Date birthDate;
    private Integer status;
    private String password;
    private Integer balance = 0;
    private Integer mapay_point = 0;


}
