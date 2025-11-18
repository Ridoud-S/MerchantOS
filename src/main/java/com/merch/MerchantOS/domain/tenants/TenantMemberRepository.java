package com.merch.MerchantOS.domain.tenants;

import com.merch.MerchantOS.domain.auth.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface TenantMemberRepository extends JpaRepository<TenantMember, String> {

    // Para el Login: Buscar si el usuario pertenece a esta tienda
    // Select * from members where email = ? AND tenant_id = ?
    Optional<TenantMember> findByUserEmailAndTenantId(String email, String tenantId);

    // Listar todos los empleados de una tienda
    List<TenantMember> findByTenantId(String tenantId);

    // Listar a qué tiendas pertenece un usuario (ej. Un dueño viendo sus 3 tiendas)
    List<TenantMember> findByUserId(String userId);

    // Validaciones
    boolean existsByUserEmailAndTenantId(String email, String tenantId);

    long countByTenantId(String tenantId);
    List<TenantMember> findByUserAndIsActiveTrue(User user);
}
