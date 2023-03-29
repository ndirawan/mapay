package com.enigma.mapay.repository;

import com.enigma.mapay.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDetailRepository extends JpaRepository<User, String> {
}
