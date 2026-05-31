package co.edu.udec.guarderia.domain.valueobjects;

public record NombreCompleto(String value) {
    public NombreCompleto {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("El nombre completo no puede estar vacio");
        }
    }
}
