package com.enigma.mapay.repository;

import com.enigma.mapay.entity.Withdraw;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface WithdrawRepository extends JpaRepository<Withdraw, String>, JpaSpecificationExecutor<Withdraw> {
}
