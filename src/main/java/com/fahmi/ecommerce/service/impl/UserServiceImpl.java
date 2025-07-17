package com.fahmi.ecommerce.service.impl;

import com.fahmi.ecommerce.constant.UserRole;
import com.fahmi.ecommerce.dto.request.PartnerRegistrationRequest;
import com.fahmi.ecommerce.dto.request.UserLoginRequest;
import com.fahmi.ecommerce.dto.request.CustomerRegistrationRequest;
import com.fahmi.ecommerce.dto.response.PartnerRegistrationResponse;
import com.fahmi.ecommerce.dto.response.UserLoginResponse;
import com.fahmi.ecommerce.dto.response.UserRegistrationResponse;
import com.fahmi.ecommerce.entity.Partner;
import com.fahmi.ecommerce.entity.Role;
import com.fahmi.ecommerce.entity.User;
import com.fahmi.ecommerce.exception.CustomException;
import com.fahmi.ecommerce.repository.PartnerRepository;
import com.fahmi.ecommerce.repository.UserRepository;
import com.fahmi.ecommerce.security.JwtUtil;
import com.fahmi.ecommerce.service.RoleService;
import com.fahmi.ecommerce.service.UserService;
import com.fahmi.ecommerce.util.TokenHolderUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final PartnerRepository partnerRepository;
    private final UserRepository userRepository;

    private final RoleService roleService;

    private final JwtUtil jwtUtil;
    private final TokenHolderUtil tokenHolderUtil;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public UserRegistrationResponse customerRegistration(CustomerRegistrationRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new CustomException.ConflictException("Username is already taken");
        }

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new CustomException.ConflictException("Email already in use");
        }

        Set<Role> roles = new HashSet<>();
        roles.add(roleService.getOrCreate(UserRole.CUSTOMER));

        User user = userRepository.save(mapToUser(request, roles));
        return mapToUserRegistrationResponse(user);
    }

    @Transactional
    @Override
    public PartnerRegistrationResponse partnerRegistration(PartnerRegistrationRequest request) {
        String username = tokenHolderUtil.getUsername();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new CustomException.ResourceNotFoundException("User not found"));

        if (partnerRepository.existsByIdCardNumber(request.getIdCardNumber())) {
            throw new CustomException.ConflictException("Card number is already in use");
        }

        Set<Role> roles = user.getRoles();
        roles.add(roleService.getOrCreate(UserRole.PARTNER));
        user.setRoles(roles);

        Partner partner = Partner.builder()
                .idCardNumber(request.getIdCardNumber())
                .bankName(request.getBankName())
                .bankAccountName(request.getBankAccountName())
                .bankAccountNumber(request.getBankAccountNumber())
                .address(request.getAddress())
                .build();
        user.setPartner(partner);

        userRepository.save(user);
        return mapToSellerRegistrationResponse(user);
    }

    @Override
    public UserLoginResponse userLogin(UserLoginRequest request) {
        if (request.getUsernameOrEmail() == null || request.getUsernameOrEmail().isEmpty() ||
                request.getPassword() == null || request.getPassword().isEmpty()) {
            throw new CustomException.BadRequestException("Username or email and password is required");
        }

        User user;
        if (request.getUsernameOrEmail().split("@").length != 2) {
            user = userRepository.findByUsername(request.getUsernameOrEmail())
                    .orElseThrow(() -> new CustomException.ResourceNotFoundException("User not found"));
        } else {
            user = userRepository.findByEmail(request.getUsernameOrEmail())
                    .orElseThrow(() -> new CustomException.ResourceNotFoundException("User not found"));
        }

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new CustomException.AuthenticationException("Email or password is incorrect.");
        }

        String token = jwtUtil.generateToken(user);
        return UserLoginResponse.builder().token(token).build();
    }

    public User mapToUser(CustomerRegistrationRequest userRegistrationRequest, Set<Role> roles) {
        return User.builder()
                .firstName(userRegistrationRequest.getFirstName())
                .lastName(userRegistrationRequest.getLastName())
                .username(userRegistrationRequest.getUsername())
                .email(userRegistrationRequest.getEmail())
                .password(passwordEncoder.encode(userRegistrationRequest.getPassword()))
                .phoneNumber(userRegistrationRequest.getPhoneNumber())
                .roles(roles)
                .isAccountNonExpired(true)
                .isAccountNonLocked(true)
                .isCredentialsNonExpired(true)
                .isEnabled(true)
                .build();
    }

    public UserRegistrationResponse mapToUserRegistrationResponse(User user) {
        return UserRegistrationResponse.builder()
                .userId(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .username(user.getUsername())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .build();
    }

    public PartnerRegistrationResponse mapToSellerRegistrationResponse(User user) {
        return PartnerRegistrationResponse.builder()
                .user(mapToUserRegistrationResponse(user))
                .idCardNumber(user.getPartner().getIdCardNumber())
                .bankName(user.getPartner().getBankName())
                .bankAccountName(user.getPartner().getBankAccountName())
                .bankAccountNumber(user.getPartner().getBankAccountNumber())
                .address(user.getPartner().getAddress())
                .build();
    }
}
