package co.edu.udec.guarderia.domain.valueobjects;

public record NinoId(String value) {
    public NinoId {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("El numero de matricula no puede estar vacio");
        }
    }
}
