package com.jcaa.usersmanagement.domain.valueobject;

import java.time.LocalDate;

public record FechaNacimiento(LocalDate value) {
    public FechaNacimiento {
        if (value == null) {
            throw new IllegalArgumentException("FechaNacimiento no puede ser nula");
        }
        if (value.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("FechaNacimiento no puede ser futura");
        }
    }
}
