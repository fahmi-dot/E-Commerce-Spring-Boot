package com.fahmi.ecommerce.service;

import com.fahmi.ecommerce.constant.UserRole;
import com.fahmi.ecommerce.entity.Role;
import java.util.Optional;

public interface RoleService {
    Optional<Role> findByName(UserRole roleName);

    Role save(Role role);

    Role getOrCreate(UserRole roleName);
}
