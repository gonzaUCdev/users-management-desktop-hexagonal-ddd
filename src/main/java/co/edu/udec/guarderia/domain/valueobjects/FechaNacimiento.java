package co.edu.udec.guarderia.domain.valueobjects;

import java.time.LocalDate;

public record FechaNacimiento(LocalDate value) {
    public FechaNacimiento {
        if (value == null) {
            throw new IllegalArgumentException("La fecha de nacimiento no puede ser nula");
        }
        if (value.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("La fecha de nacimiento no puede ser una fecha futura");
        }
    }
}
