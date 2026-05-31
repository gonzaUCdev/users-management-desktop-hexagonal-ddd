package co.edu.udec.guarderia.domain.valueobjects;

public record MenuId(Integer value) {
    public MenuId {
        if (value == null || value <= 0) {
            throw new IllegalArgumentException("El ID del menu debe ser un numero positivo");
        }
    }
}
