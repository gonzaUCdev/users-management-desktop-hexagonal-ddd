package co.edu.udec.guarderia.domain.valueobjects;

public record NombreIngrediente(String value) {
    public NombreIngrediente {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("El nombre del ingrediente no puede estar vacio");
        }
    }
}
