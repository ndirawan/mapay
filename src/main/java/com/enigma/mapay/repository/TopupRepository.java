package com.enigma.mapay.repository;

import com.enigma.mapay.entity.Topup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

public interface TopupRepository extends JpaRepository<Topup, String>, JpaSpecificationExecutor<Topup> {
    Optional<List<Topup>> findTopupByUserId(String id);
    Optional<List<Topup>> findTopupByUserPhoneNumber(String id);

}
