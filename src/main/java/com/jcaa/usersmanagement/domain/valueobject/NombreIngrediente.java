package com.jcaa.usersmanagement.domain.valueobject;

public record NombreIngrediente(String value) {
    public NombreIngrediente {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("NombreIngrediente no puede estar vacio");
        }
    }
}
