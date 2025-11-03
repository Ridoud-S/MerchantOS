package com.merch.MerchantOS.domain.tenants;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TenantRepository extends JpaRepository<Tenant, String> {
    boolean existsByName(String name);
    Optional<Tenant> findByName(String name);


    Optional<Tenant> findByOwnerId(String ownerId);
    List<Tenant> findByOwnerId(String ownerId);

}
