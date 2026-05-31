package com.jcaa.usersmanagement.domain.valueobject;

public record NombreCompleto(String value) {
    public NombreCompleto {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("NombreCompleto no puede estar vacio");
        }
    }
}
