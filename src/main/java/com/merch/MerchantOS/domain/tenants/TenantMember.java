package com.merch.MerchantOS.domain.tenants;

import com.merch.MerchantOS.domain.auth.Role;
import com.merch.MerchantOS.domain.auth.User;
import com.merch.MerchantOS.shared.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tenant_members", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"user_id", "tenant_id"}) // Un usuario no puede duplicarse en la misma tienda
})
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class TenantMember extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tenant_id", nullable = false)
    private Tenant tenant;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role; // ADMIN, MANAGER, SUPPORT

    @Column(name = "is_active")
    @Builder.Default
    private Boolean isActive = true;
}