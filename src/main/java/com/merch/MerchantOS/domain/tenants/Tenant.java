package com.merch.MerchantOS.domain.tenants;

import com.merch.MerchantOS.infrastructure.shared.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tenants")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Tenant extends BaseEntity {

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Plan plan;

    @Column(name = "owner_id", nullable = false)
    private String ownerId;

    public enum Plan {
        FREE,
        STARTER,
        PRO,
        ENTERPRISE
    }
}
