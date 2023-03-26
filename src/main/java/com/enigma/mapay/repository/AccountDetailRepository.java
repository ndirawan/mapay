package com.enigma.mapay.repository;

import com.enigma.mapay.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountDetailRepository extends JpaRepository<Account, String> {
}
