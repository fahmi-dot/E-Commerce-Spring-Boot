package com.fahmi.ecommerce.service.impl;

import com.fahmi.ecommerce.constant.UserRole;
import com.fahmi.ecommerce.entity.Role;
import com.fahmi.ecommerce.repository.RoleRepository;
import com.fahmi.ecommerce.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    @Override
    public Optional<Role> findByName(UserRole roleName) {
        return roleRepository.findByName(roleName);
    }

    @Override
    public Role save(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public Role getOrCreate(UserRole roleName) {
        return findByName(roleName)
                .orElseGet(() -> {
                    Role role = new Role();
                    role.setName(roleName);
                    return save(role);
                });
    }
}
