package com.enigma.mapay.repository;

import com.enigma.mapay.entity.SplitBill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface SplitBIllRepository extends JpaRepository<SplitBill, String>, JpaSpecificationExecutor<SplitBill> {
}
