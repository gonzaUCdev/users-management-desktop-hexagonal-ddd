package co.edu.udec.guarderia.domain.valueobjects;

public record Telefono(String value) {
    public Telefono {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("El telefono no puede estar vacio");
        }
    }
}
