package com.enigma.mapay.repository;


import com.enigma.mapay.entity.TransferDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransferDetailRepository extends JpaRepository<TransferDetail, String> {
}
