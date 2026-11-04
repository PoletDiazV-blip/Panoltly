package com.polet.panoltli.service.impl;

import com.polet.panoltli.config.JwtUtil;
import com.polet.panoltli.dto.LoginRequestDto;
import com.polet.panoltli.dto.LoginResponseDto;
import com.polet.panoltli.dto.RegisterRequestDto;
import com.polet.panoltli.dto.UserResponseDto;
import com.polet.panoltli.entity.RoleEntity;
import com.polet.panoltli.entity.UserEntity;
import com.polet.panoltli.repository.RoleRepository;
import com.polet.panoltli.repository.UserRepository;
import com.polet.panoltli.service.AuthService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;

    public AuthServiceImpl(UserRepository userRepository, RoleRepository roleRepository,
                           PasswordEncoder passwordEncoder, JwtUtil jwtUtil,
                           AuthenticationManager authenticationManager, UserDetailsService userDetailsService) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
    }

    @Override
    public UserResponseDto register(RegisterRequestDto request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("El correo ya está registrado");
        }

        UserEntity user = new UserEntity();
        user.setUsername(request.getNombre());
        user.setEmail(request.getEmail());
        user.setPasswordHash(passwordEncoder.encode(request.getPassword()));
        user.setIsActive(true);

        RoleEntity userRole = roleRepository.findByName("ROLE_USER")
                .orElseGet(() -> {
                    RoleEntity newRole = new RoleEntity();
                    newRole.setName("ROLE_USER");
                    newRole.setDescription("Usuario estándar de la plataforma Panoltli");
                    return roleRepository.save(newRole);
                });

        Set<RoleEntity> roles = new HashSet<>();
        roles.add(userRole);
        user.setRoles(roles);

        UserEntity savedUser = userRepository.save(user);

        return new UserResponseDto(
                savedUser.getId() != null ? savedUser.getId().toString() : null,
                savedUser.getUsername(),
                savedUser.getEmail(),
                savedUser.getRoles().stream().map(RoleEntity::getName).collect(Collectors.toSet())
        );
    }

    @Override
    public LoginResponseDto login(LoginRequestDto request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
        String token = jwtUtil.generateToken(userDetails);

        return new LoginResponseDto(token, token, "Bearer");
    }
}