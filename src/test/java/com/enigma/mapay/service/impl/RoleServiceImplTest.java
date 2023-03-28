package com.enigma.mapay.service.impl;

import com.enigma.mapay.dto.ERole;
import com.enigma.mapay.entity.Role;
import com.enigma.mapay.repository.RoleRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.enigma.mapay.dto.ERole.ROLE_USER;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RoleServiceImplTest {
    @Mock
    RoleRepository roleRepository;

    @InjectMocks
    RoleServiceImpl roleService;

    @Test
    void getOrSave() {
        ERole eRole = ROLE_USER;
        Role role = new Role("01", eRole);
        when(roleRepository.findByRole(eRole)).thenReturn(Optional.of(Optional.of(role).get()));
        Role roles = roleService.getOrSave(ROLE_USER);
        assertEquals(role.getRole(),roles.getRole());

        verify(roleRepository,times(1)).findByRole(eRole);
    }
}