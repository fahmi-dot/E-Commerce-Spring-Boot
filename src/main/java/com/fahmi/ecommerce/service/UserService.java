package com.fahmi.ecommerce.service;

import com.fahmi.ecommerce.constant.UserRole;
import com.fahmi.ecommerce.model.dto.request.SellerRegistrationRequest;
import com.fahmi.ecommerce.model.dto.request.UserLoginRequest;
import com.fahmi.ecommerce.model.dto.request.UserRegistrationRequest;
import com.fahmi.ecommerce.model.dto.response.SellerRegistrationResponse;
import com.fahmi.ecommerce.model.dto.response.UserLoginResponse;
import com.fahmi.ecommerce.model.dto.response.UserRegistrationResponse;
import com.fahmi.ecommerce.model.entity.Role;
import com.fahmi.ecommerce.model.entity.User;
import com.fahmi.ecommerce.repository.UserRepository;
import com.fahmi.ecommerce.util.JwtUtil;
import com.fahmi.ecommerce.util.TokenHolderUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final RoleService roleService;
    private final JwtUtil jwtUtil;
    private final TokenHolderUtil tokenHolderUtil;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public UserRegistrationResponse customerRegistration(UserRegistrationRequest userRegistrationRequest) {
        if (userRepository.existsByUsername(userRegistrationRequest.getUsername())) {
            throw new RuntimeException("Username is already in use");
        }
        if (userRepository.existsByEmail(userRegistrationRequest.getEmail())) {
            throw new RuntimeException("Email is already in use");
        }

        Role customerRole = roleService.getOrCreate(UserRole.CUSTOMER);

        Set<Role> roles = new HashSet<>();
        roles.add(customerRole);

        return mapToUserRegistrationResponse(
                userRepository.save(mapToUser(userRegistrationRequest, roles)));
    }

    @Transactional
    public SellerRegistrationResponse sellerRegistration(SellerRegistrationRequest sellerRegistrationRequest) {
        String username = tokenHolderUtil.getUsername();
        Optional<User> user = userRepository.findByUsername(username);

        if (!user.isPresent()) {
            Set<Role> roles = user.get().getRoles();
            roles.add(roleService.getOrCreate(UserRole.SELLER));
            user.get().setRoles(roles);
            userRepository.save(user.get());
            return mapToSellerRegistrationResponse(user.get());
        }

        return null;
    }

    public UserLoginResponse userLogin(UserLoginRequest userLoginRequest) {
        User user = userRepository.findByUsername(userLoginRequest.getUsername())
                .orElseThrow(() -> new RuntimeException("Username or password is incorrect"));

        if (passwordEncoder.matches(userLoginRequest.getPassword(), user.getPassword())) {
            String token = jwtUtil.generateToken(userLoginRequest.getUsername());
            return mapToUserLoginResponse(token);
        }

        return null;
    }

    public User mapToUser(UserRegistrationRequest userRegistrationRequest, Set<Role> roles) {
        return User.builder()
                .fullName(userRegistrationRequest.getFullName())
                .email(userRegistrationRequest.getEmail())
                .username(userRegistrationRequest.getUsername())
                .password(passwordEncoder.encode(userRegistrationRequest.getPassword()))
                .roles(roles)
                .isAccountNonExpired(true)
                .isAccountNonLocked(true)
                .isCredentialsNonExpired(true)
                .isEnabled(true)
                .build();
    }

    public UserRegistrationResponse mapToUserRegistrationResponse(User user) {
        return UserRegistrationResponse.builder()
                .message("Welcome " + user.getUsername() + "!")
                .build();
    }

    public SellerRegistrationResponse mapToSellerRegistrationResponse(User user) {
        return SellerRegistrationResponse.builder()
                .message("Yeay! Now you're a seller!")
                .build();
    }

    public UserLoginResponse mapToUserLoginResponse(String value) {
        return UserLoginResponse.builder()
                .token(value)
                .build();
    }
}
