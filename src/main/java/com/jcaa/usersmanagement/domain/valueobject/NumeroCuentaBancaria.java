package com.jcaa.usersmanagement.domain.valueobject;

public record NumeroCuentaBancaria(String value) {
    public NumeroCuentaBancaria {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("NumeroCuentaBancaria no puede estar vacio");
        }
    }
}
