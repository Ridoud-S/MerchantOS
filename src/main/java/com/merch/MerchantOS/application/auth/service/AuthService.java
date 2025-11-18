package com.merch.MerchantOS.application.auth.service;

import com.merch.MerchantOS.domain.AuthProvider;
import com.merch.MerchantOS.domain.auth.*;
import com.merch.MerchantOS.domain.auth.dto.*;
import com.merch.MerchantOS.domain.tenants.*;
import com.merch.MerchantOS.infrastructure.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final TenantRepository tenantRepository;
    private final TenantMemberRepository tenantMemberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    @Transactional
    public AuthResponse register(RegisterRequest request) {

        // 1. Validaciones previas
        if (userRepository.existsByEmail(request.email())) {
            throw new RuntimeException("El email ya está registrado");
        }
        if (tenantRepository.existsBySubdomain(request.subdomain())) {
            throw new RuntimeException("El subdominio ya está ocupado");
        }

        // 2. Crear el Usuario Global
        User user = User.builder()
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .provider(AuthProvider.LOCAL)
                .build();

        userRepository.save(user);

        // 3. Crear el Tenant (La Tienda)
        Tenant tenant = Tenant.builder()
                .name(request.storeName())
                .subdomain(request.subdomain())
                .plan(Plan.FREE)
                .ownerId(user.getId())
                .build();

        tenantRepository.save(tenant);

        // 4. Crear la Membresía (El vínculo)
        TenantMember member = TenantMember.builder()
                .user(user)
                .tenant(tenant)
                .role(Role.TENANT_ADMIN)
                .isActive(true)
                .build();

        tenantMemberRepository.save(member);

        // 5. Generar Token y devolver respuesta
        String token = jwtTokenProvider.generateToken(
                user.getEmail(),
                tenant.getId(),
                user.getId()
        );

        return new AuthResponse(
                token,
                user.getEmail(),
                tenant.getId(),
                Role.TENANT_ADMIN.name()
        );
    }

    @Transactional(readOnly = true)
    public AuthResponse login(LoginRequest request) {

        // 1. Buscar usuario por email
        User user = userRepository.findByEmail(request.email())
                .orElseThrow(() -> {
                    System.out.println(" Usuario no encontrado con email: " + request.email());
                    return new RuntimeException("Email o contraseña incorrectos");
                });

        System.out.println(" Usuario encontrado: " + user.getEmail());

        // 2. Validar contraseña
        if (!passwordEncoder.matches(request.password(), user.getPassword())) {
            System.out.println(" Contraseña incorrecta para: " + user.getEmail());
            throw new RuntimeException("Email o contraseña incorrectos");
        }

        System.out.println("Contraseña válida");

        // 3. Buscar la primera membresía activa del usuario
        TenantMember activeMember = tenantMemberRepository
                .findByUserAndIsActiveTrue(user)
                .stream()
                .findFirst()
                .orElseThrow(() -> {
                    System.out.println("Usuario sin tenant activo: " + user.getEmail());
                    return new RuntimeException("Usuario sin tienda activa");
                });

        System.out.println("Tenant encontrado: " + activeMember.getTenant().getName());

        // 4. Generar Token
        String token = jwtTokenProvider.generateToken(
                user.getEmail(),
                activeMember.getTenant().getId(),
                user.getId()
        );

        // 5. Devolver respuesta
        return new AuthResponse(
                token,
                user.getEmail(),
                activeMember.getTenant().getId(),
                activeMember.getRole().name()
        );
    }
}