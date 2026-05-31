package com.jcaa.usersmanagement.domain.valueobject;

public record DNI(String value) {
    public DNI {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("DNI no puede estar vacio");
        }
    }
}
