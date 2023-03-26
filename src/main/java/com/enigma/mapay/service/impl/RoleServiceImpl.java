package com.enigma.mapay.service.impl;

import com.enigma.mapay.dto.ERole;
import com.enigma.mapay.entity.Role;
import com.enigma.mapay.repository.RoleRepository;
import com.enigma.mapay.service.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public Role getOrSave(ERole role) {
        return roleRepository.findByRole(role)
                .orElseGet(() -> roleRepository.save(new Role(null, role)));
    }
}
