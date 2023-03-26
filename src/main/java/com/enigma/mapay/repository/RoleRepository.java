package com.enigma.mapay.repository;

import com.enigma.mapay.dto.ERole;
import com.enigma.mapay.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, String> {
    Optional<Role> findByRole(ERole role);
}
