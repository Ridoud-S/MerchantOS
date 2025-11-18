package com.merch.MerchantOS.domain.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

//Los paréntesis definen los campos.
public record RegisterRequest(

        @NotBlank(message = "El email es obligatorio")
        @Email(message = "Formato de email inválido")
        String email,

        @NotBlank(message = "La contraseña es obligatoria")
        @Size(min = 6, message = "La contraseña debe tener al menos 6 caracteres")
        String password,

        @NotBlank(message = "El nombre de la tienda es obligatorio")
        String storeName,

        @NotBlank(message = "El subdominio es obligatorio")
        String subdomain
) {}