package com.fahmi.ecommerce.repository;

import com.fahmi.ecommerce.constant.UserRole;
import com.fahmi.ecommerce.entity.Role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {
    Optional<Role> findByName(UserRole role);
}
