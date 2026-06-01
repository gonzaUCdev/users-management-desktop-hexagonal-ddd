package co.edu.udec.guarderia.domain.valueobjects;

public record Direccion(String value) {
    public Direccion {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("La direccion no puede estar vacia");
        }
    }
}
