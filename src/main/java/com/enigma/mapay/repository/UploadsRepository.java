package com.enigma.mapay.repository;

import com.enigma.mapay.entity.Upload;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UploadsRepository extends JpaRepository<Upload, Long> {
}
