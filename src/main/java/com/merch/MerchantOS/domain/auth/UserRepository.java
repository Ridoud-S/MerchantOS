package com.merch.MerchantOS.domain.auth;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    // 🔥 Login en Admin Dashboard
    Optional<User> findByEmailAndTenantId(String email, String tenantId);

    // 🔥 Validar si email ya existe en ESTA tienda
    boolean existsByEmailAndTenantId(String email, String tenantId);

    // 🔥 Listar empleados de una tienda
    List<User> findByTenantId(String tenantId);

    // 🔥 Contar empleados (para límites del plan)
    long countByTenantId(String tenantId);

    // Obtener solo managers o admins
    List<User> findByTenantIdAndRole(String tenantId, Role role);
}
