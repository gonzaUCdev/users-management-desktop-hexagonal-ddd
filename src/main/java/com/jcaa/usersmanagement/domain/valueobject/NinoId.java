package com.jcaa.usersmanagement.domain.valueobject;

public record NinoId(String value) {
    public NinoId {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("NinoId no puede estar vacio");
        }
    }
}
