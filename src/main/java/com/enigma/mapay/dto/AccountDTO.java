package com.enigma.mapay.dto;

import com.enigma.mapay.entity.Account;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class AccountDTO {
    private String phoneNumber;
    private List<String> roles;

    public AccountDTO(Account account) {
        this.phoneNumber = account.getPhoneNumber();
        this.roles = account.getRoles().stream().map(role -> role.getRole().name())
                .collect(Collectors.toList());
    }
}
