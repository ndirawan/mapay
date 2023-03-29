package com.enigma.mapay.repository;

import com.enigma.mapay.entity.Upload;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UploadRepository extends JpaRepository<Upload, String> {
}
