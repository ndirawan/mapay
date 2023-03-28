package com.enigma.mapay.repository;

import com.enigma.mapay.entity.WithdrawDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WithdrawDetailRepository extends JpaRepository<WithdrawDetail, String> {
}
