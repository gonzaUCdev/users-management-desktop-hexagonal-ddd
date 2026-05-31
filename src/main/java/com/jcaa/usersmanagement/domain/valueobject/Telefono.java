package com.jcaa.usersmanagement.domain.valueobject;

public record Telefono(String value) {
    public Telefono {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Telefono no puede estar vacio");
        }
    }
}
