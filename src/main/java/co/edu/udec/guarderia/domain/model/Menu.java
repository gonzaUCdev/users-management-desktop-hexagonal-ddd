package co.edu.udec.guarderia.domain.model;

import co.edu.udec.guarderia.domain.valueobjects.MenuId;
import co.edu.udec.guarderia.domain.valueobjects.NombreIngrediente;
import co.edu.udec.guarderia.domain.valueobjects.NombrePlato;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Menu {
    private final MenuId id;
    private final List<Plato> platos;

    public Menu(MenuId id, List<Plato> platos) {
        if (id == null) throw new IllegalArgumentException("El ID del menu no puede ser nulo");
        if (platos == null) throw new IllegalArgumentException("La lista de platos no puede ser nula");

        this.id = id;
        this.platos = new ArrayList<>(platos);
    }

    public void agregarPlato(Plato plato) {
        if (plato == null) throw new IllegalArgumentException("El plato a agregar no puede ser nulo");
        if (platos.contains(plato)) {
            throw new IllegalArgumentException("El plato ya existe en el menu");
        }
        platos.add(plato);
    }

    public void eliminarPlato(NombrePlato nombrePlato) {
        if (nombrePlato == null) throw new IllegalArgumentException("El nombre del plato no puede ser nulo");
        platos.removeIf(p -> p.getNombre().equals(nombrePlato));
    }

    public boolean contienePlato(NombrePlato nombrePlato) {
        if (nombrePlato == null) throw new IllegalArgumentException("El nombre del plato no puede ser nulo");
        return platos.stream().anyMatch(p -> p.getNombre().equals(nombrePlato));
    }

    public boolean contieneIngrediente(NombreIngrediente ingrediente) {
        if (ingrediente == null) throw new IllegalArgumentException("El ingrediente no puede ser nulo");
        return platos.stream().anyMatch(p -> p.contieneIngrediente(ingrediente));
    }

    public BigDecimal getPrecioTotal() {
        return platos.stream()
                .map(Plato::getPrecio)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public MenuId getId() { return id; }
    public List<Plato> getPlatos() { return Collections.unmodifiableList(platos); }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Menu that)) return false;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
