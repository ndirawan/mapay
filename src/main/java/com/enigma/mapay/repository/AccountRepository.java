package com.enigma.mapay.repository;

import com.enigma.mapay.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, String> {
    Optional<Account> findByPhoneNumber(String phoneNumber);
}
