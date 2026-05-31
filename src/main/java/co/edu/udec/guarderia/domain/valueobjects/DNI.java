package co.edu.udec.guarderia.domain.valueobjects;

public record DNI(String value) {
    public DNI {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("El DNI no puede estar vacio");
        }
    }
}
