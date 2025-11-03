package com.merch.MerchantOS.domain.tenants;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "tenants")
@Data @NoArgsConstructor @AllArgsConstructor
@Builder
public class Tenant {
    @Id
    private String id;

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Plan plan;

    @Column(name = "owner_id", nullable = false) // ← NUEVO CAMPO IMPORTANTE
    private String ownerId;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist() {
        if (id == null) {
            id = UUID.randomUUID().toString();
        }
        if (createdAt == null) {
            createdAt = LocalDateTime.now();
        }
    }

    public enum Plan {
        FREE, STARTER, PRO, ENTERPRISE
    }
}