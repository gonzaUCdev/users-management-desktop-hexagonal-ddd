package co.edu.udec.guarderia.domain.model;

import co.edu.udec.guarderia.domain.enums.TipoRelacion;
import co.edu.udec.guarderia.domain.valueobjects.DNI;
import co.edu.udec.guarderia.domain.valueobjects.Direccion;
import co.edu.udec.guarderia.domain.valueobjects.NombreCompleto;
import co.edu.udec.guarderia.domain.valueobjects.Telefono;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class PersonaAutorizada {
    private final DNI dni;
    private final NombreCompleto nombre;
    private final Direccion direccion;
    private final List<Telefono> telefonos;
    private final TipoRelacion relacion;

    public PersonaAutorizada(DNI dni, NombreCompleto nombre, Direccion direccion,
                             List<Telefono> telefonos, TipoRelacion relacion) {
        if (dni == null) throw new IllegalArgumentException("El DNI de la persona autorizada no puede ser nulo");
        if (nombre == null) throw new IllegalArgumentException("El nombre de la persona autorizada no puede ser nulo");
        if (direccion == null) throw new IllegalArgumentException("La direccion de la persona autorizada no puede ser nula");
        if (telefonos == null || telefonos.isEmpty()) {
            throw new IllegalArgumentException("La persona autorizada debe tener al menos un telefono de contacto");
        }
        if (relacion == null) throw new IllegalArgumentException("La relacion con el nino no puede ser nula");

        this.dni = dni;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefonos = List.copyOf(telefonos);
        this.relacion = relacion;
    }

    public DNI getDni() { return dni; }
    public NombreCompleto getNombre() { return nombre; }
    public Direccion getDireccion() { return direccion; }
    public List<Telefono> getTelefonos() { return Collections.unmodifiableList(telefonos); }
    public TipoRelacion getRelacion() { return relacion; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PersonaAutorizada that)) return false;
        return dni.equals(that.dni);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dni);
    }
}
