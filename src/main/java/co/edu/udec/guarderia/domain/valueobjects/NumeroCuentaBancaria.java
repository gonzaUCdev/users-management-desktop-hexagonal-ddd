package co.edu.udec.guarderia.domain.valueobjects;

public record NumeroCuentaBancaria(String value) {
    public NumeroCuentaBancaria {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("El numero de cuenta bancaria no puede estar vacio");
        }
    }
}
