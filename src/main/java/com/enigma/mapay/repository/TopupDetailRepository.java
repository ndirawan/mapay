package com.enigma.mapay.repository;

import com.enigma.mapay.entity.Topup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TopupDetailRepository extends JpaRepository<Topup, String>, JpaSpecificationExecutor<Topup> {
}
