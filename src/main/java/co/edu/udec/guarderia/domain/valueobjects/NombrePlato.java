package co.edu.udec.guarderia.domain.valueobjects;

public record NombrePlato(String value) {
    public NombrePlato {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("El nombre del plato no puede estar vacio");
        }
    }
}
