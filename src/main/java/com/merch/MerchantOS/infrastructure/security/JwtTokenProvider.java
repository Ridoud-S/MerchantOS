package com.merch.MerchantOS.infrastructure.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenProvider {

    @Value("${jwt.secret.key}")
    private String token;

    @Value("${jwt.time.expiration}")
    private long jwtExpiration;




}
