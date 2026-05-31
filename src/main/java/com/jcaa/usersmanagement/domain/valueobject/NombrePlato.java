package com.jcaa.usersmanagement.domain.valueobject;

public record NombrePlato(String value) {
    public NombrePlato {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("NombrePlato no puede estar vacio");
        }
    }
}
