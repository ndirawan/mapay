package com.enigma.mapay.service;

import com.enigma.mapay.dto.ERole;
import com.enigma.mapay.entity.Role;

public interface RoleService {
    Role getOrSave(ERole role);
}
