package co.edu.udec.guarderia.domain.model;

import co.edu.udec.guarderia.domain.valueobjects.NombreIngrediente;
import co.edu.udec.guarderia.domain.valueobjects.NombrePlato;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Plato {
    private final NombrePlato nombre;
    private final List<NombreIngrediente> ingredientes;
    private final BigDecimal precio;

    public Plato(NombrePlato nombre, List<NombreIngrediente> ingredientes, BigDecimal precio) {
        if (nombre == null) throw new IllegalArgumentException("El nombre del plato no puede ser nulo");
        if (ingredientes == null || ingredientes.isEmpty()) {
            throw new IllegalArgumentException("El plato debe tener al menos un ingrediente");
        }
        if (precio == null) throw new IllegalArgumentException("El precio del plato no puede ser nulo");
        if (precio.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("El precio del plato no puede ser negativo");
        }

        this.nombre = nombre;
        this.ingredientes = List.copyOf(ingredientes);
        this.precio = precio;
    }

    public NombrePlato getNombre() { return nombre; }
    public List<NombreIngrediente> getIngredientes() { return Collections.unmodifiableList(ingredientes); }
    public BigDecimal getPrecio() { return precio; }

    public boolean contieneIngrediente(NombreIngrediente ingrediente) {
        return ingredientes.contains(ingrediente);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Plato that)) return false;
        return nombre.equals(that.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre);
    }
}
