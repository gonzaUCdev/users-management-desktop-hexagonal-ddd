package com.jcaa.usersmanagement.domain.valueobject;

public record Direccion(String value) {
    public Direccion {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Direccion no puede estar vacia");
        }
    }
}
