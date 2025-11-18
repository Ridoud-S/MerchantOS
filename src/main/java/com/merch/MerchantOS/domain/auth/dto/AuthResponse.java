package com.merch.MerchantOS.domain.auth.dto;

public record AuthResponse(
        String token,
        String email,
        String tenantId,
        String role
) {
}
