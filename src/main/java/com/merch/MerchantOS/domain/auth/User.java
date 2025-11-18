package com.merch.MerchantOS.domain.auth;

import com.merch.MerchantOS.domain.AuthProvider;
import com.merch.MerchantOS.shared.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class User extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String email;

    // Puede ser nulo si se registra con Google/OAuth2 en el futuro
    @Column(nullable = true)
    private String password;

    // Para saber si es LOCAL, GOOGLE, GITHUB, etc.
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AuthProvider provider;

    // ID del usuario en Google/Github (para cuando hagas OAuth2)
    @Column(name = "provider_id")
    private String providerId;
}