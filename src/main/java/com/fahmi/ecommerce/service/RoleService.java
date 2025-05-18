package com.fahmi.ecommerce.service;

import com.fahmi.ecommerce.constant.UserRole;
import com.fahmi.ecommerce.model.entity.Role;
import com.fahmi.ecommerce.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class RoleService {
    private final RoleRepository roleRepository;

    public Optional<Role> findByName(UserRole roleName) {
        return roleRepository.findByName(roleName);
    }

    public Role save(Role role) {
        return roleRepository.save(role);
    }

    public Role getOrCreate(UserRole roleName) {
        return findByName(roleName)
                .orElseGet(() -> {
                    Role role = new Role();
                    role.setName(roleName);
                    return save(role);
                });
    }
}
