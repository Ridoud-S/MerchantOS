package com.merch.MerchantOS.domain.tenants;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TenantRepository extends JpaRepository<Tenant, String> {

    // Buscar tienda por subdominio (Storefront usa esto)
    Optional<Tenant> findBySubdomain(String subdomain);
    boolean existsBySubdomain(String subdomain);

    // Ver qué tienda(s) tiene un usuario
    Optional<Tenant> findByOwnerId(String ownerId);  // Si 1 user = 1 tienda
    // List<Tenant> findByOwnerId(String ownerId);   // Si multi-tienda

    // Validaciones
    boolean existsByName(String name);

    // Analytics
    long countByPlan(Plan plan);
}
