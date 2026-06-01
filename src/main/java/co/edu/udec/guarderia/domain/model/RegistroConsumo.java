package co.edu.udec.guarderia.domain.model;

import co.edu.udec.guarderia.domain.valueobjects.MenuId;

import java.time.LocalDate;
import java.util.Objects;

public class RegistroConsumo {
    private final LocalDate fecha;
    private final MenuId menuId;
    private final boolean comio;

    public RegistroConsumo(LocalDate fecha, MenuId menuId, boolean comio) {
        if (fecha == null) throw new IllegalArgumentException("La fecha de consumo no puede ser nula");
        if (menuId == null) throw new IllegalArgumentException("El ID del menu consumido no puede ser nulo");

        this.fecha = fecha;
        this.menuId = menuId;
        this.comio = comio;
    }

    public LocalDate getFecha() { return fecha; }
    public MenuId getMenuId() { return menuId; }
    public boolean isComio() { return comio; }

    public boolean ocurrioEnMes(int anio, int mes) {
        return fecha.getYear() == anio && fecha.getMonthValue() == mes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RegistroConsumo that)) return false;
        return fecha.equals(that.fecha) && menuId.equals(that.menuId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fecha, menuId);
    }
}
