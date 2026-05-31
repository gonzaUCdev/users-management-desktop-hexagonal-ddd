package co.edu.udec.guarderia.domain.model;

import co.edu.udec.guarderia.domain.valueobjects.DNI;
import co.edu.udec.guarderia.domain.valueobjects.Direccion;
import co.edu.udec.guarderia.domain.valueobjects.FechaNacimiento;
import co.edu.udec.guarderia.domain.valueobjects.MenuId;
import co.edu.udec.guarderia.domain.valueobjects.NinoId;
import co.edu.udec.guarderia.domain.valueobjects.NombreCompleto;
import co.edu.udec.guarderia.domain.valueobjects.NombreIngrediente;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class Nino {
    private final NinoId id;
    private final NombreCompleto nombre;
    private final FechaNacimiento fechaNacimiento;
    private final LocalDate fechaIngreso;
    private LocalDate fechaBaja;
    private final Direccion direccion;
    private final Set<NombreIngrediente> alergias;
    private final List<PersonaAutorizada> personasAutorizadas;
    private ResponsablePago responsablePago;
    private final List<RegistroConsumo> registrosConsumo;

    public Nino(NinoId id, NombreCompleto nombre, FechaNacimiento fechaNacimiento,
                LocalDate fechaIngreso, Direccion direccion) {
        if (id == null) throw new IllegalArgumentException("El ID del nino no puede ser nulo");
        if (nombre == null) throw new IllegalArgumentException("El nombre del nino no puede ser nulo");
        if (fechaNacimiento == null) throw new IllegalArgumentException("La fecha de nacimiento no puede ser nula");
        if (fechaIngreso == null) throw new IllegalArgumentException("La fecha de ingreso no puede ser nula");
        if (direccion == null) throw new IllegalArgumentException("La direccion del nino no puede ser nula");

        if (fechaIngreso.isBefore(fechaNacimiento.value())) {
            throw new IllegalArgumentException("La fecha de ingreso no puede ser anterior a la fecha de nacimiento");
        }

        this.id = id;
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.fechaIngreso = fechaIngreso;
        this.fechaBaja = null;
        this.direccion = direccion;
        this.alergias = new HashSet<>();
        this.personasAutorizadas = new ArrayList<>();
        this.responsablePago = null;
        this.registrosConsumo = new ArrayList<>();
    }

    public void darDeBaja(LocalDate fecha) {
        if (fecha == null) throw new IllegalArgumentException("La fecha de baja no puede ser nula");
        if (fecha.isBefore(fechaIngreso)) {
            throw new IllegalArgumentException("La fecha de baja no puede ser anterior a la fecha de ingreso");
        }
        if (this.fechaBaja != null) {
            throw new IllegalStateException("El nino ya fue dado de baja el " + this.fechaBaja);
        }
        this.fechaBaja = fecha;
    }

    public void registrarAlergia(NombreIngrediente ingrediente) {
        if (ingrediente == null) throw new IllegalArgumentException("El ingrediente no puede ser nulo");
        if (alergias.contains(ingrediente)) {
            throw new IllegalArgumentException("La alergia al ingrediente ya esta registrada");
        }
        alergias.add(ingrediente);
    }

    public void eliminarAlergia(NombreIngrediente ingrediente) {
        if (ingrediente == null) throw new IllegalArgumentException("El ingrediente no puede ser nulo");
        alergias.remove(ingrediente);
    }

    public boolean tieneAlergiaA(NombreIngrediente ingrediente) {
        if (ingrediente == null) throw new IllegalArgumentException("El ingrediente no puede ser nulo");
        return alergias.contains(ingrediente);
    }

    public boolean tieneAlergias() {
        return !alergias.isEmpty();
    }

    public boolean puedeComerPlato(Plato plato) {
        if (plato == null) throw new IllegalArgumentException("El plato no puede ser nulo");
        return plato.getIngredientes().stream().noneMatch(this::tieneAlergiaA);
    }

    public void agregarPersonaAutorizada(PersonaAutorizada persona) {
        if (persona == null) throw new IllegalArgumentException("La persona autorizada no puede ser nula");
        boolean yaExiste = personasAutorizadas.stream()
                .anyMatch(p -> p.getDni().equals(persona.getDni()));
        if (yaExiste) {
            throw new IllegalArgumentException("Ya existe una persona autorizada con DNI " + persona.getDni().value());
        }
        personasAutorizadas.add(persona);
    }

    public void eliminarPersonaAutorizada(DNI dni) {
        if (dni == null) throw new IllegalArgumentException("El DNI no puede ser nulo");
        personasAutorizadas.removeIf(p -> p.getDni().equals(dni));
    }

    public List<PersonaAutorizada> getPersonasAutorizadasFamiliares() {
        return personasAutorizadas.stream()
                .filter(p -> p.getRelacion().esFamiliar())
                .collect(Collectors.toUnmodifiableList());
    }

    public void asignarResponsablePago(ResponsablePago responsable) {
        if (responsable == null) throw new IllegalArgumentException("El responsable de pago no puede ser nulo");
        this.responsablePago = responsable;
    }

    public boolean estaActivo() {
        return fechaBaja == null;
    }

    public void agregarRegistroConsumo(RegistroConsumo registro) {
        if (registro == null) throw new IllegalArgumentException("El registro de consumo no puede ser nulo");
        boolean yaExiste = registrosConsumo.stream()
                .anyMatch(r -> r.getFecha().equals(registro.getFecha())
                        && r.getMenuId().equals(registro.getMenuId()));
        if (yaExiste) {
            throw new IllegalArgumentException("Ya existe un registro de consumo para este menu en esta fecha");
        }
        registrosConsumo.add(registro);
    }

    public long getDiasComioEnMes(int anio, int mes) {
        return registrosConsumo.stream()
                .filter(RegistroConsumo::isComio)
                .filter(r -> r.ocurrioEnMes(anio, mes))
                .count();
    }

    public boolean comioEnFecha(LocalDate fecha) {
        if (fecha == null) throw new IllegalArgumentException("La fecha no puede ser nula");
        return registrosConsumo.stream()
                .filter(RegistroConsumo::isComio)
                .anyMatch(r -> r.getFecha().equals(fecha));
    }

    public boolean comioMenuEnFecha(MenuId menuId, LocalDate fecha) {
        if (menuId == null) throw new IllegalArgumentException("El menuId no puede ser nulo");
        if (fecha == null) throw new IllegalArgumentException("La fecha no puede ser nula");
        return registrosConsumo.stream()
                .filter(RegistroConsumo::isComio)
                .anyMatch(r -> r.getFecha().equals(fecha) && r.getMenuId().equals(menuId));
    }

    public NinoId getId() { return id; }
    public NombreCompleto getNombre() { return nombre; }
    public FechaNacimiento getFechaNacimiento() { return fechaNacimiento; }
    public LocalDate getFechaIngreso() { return fechaIngreso; }
    public LocalDate getFechaBaja() { return fechaBaja; }
    public Direccion getDireccion() { return direccion; }
    public Set<NombreIngrediente> getAlergias() { return Collections.unmodifiableSet(alergias); }
    public List<PersonaAutorizada> getPersonasAutorizadas() { return Collections.unmodifiableList(personasAutorizadas); }
    public ResponsablePago getResponsablePago() { return responsablePago; }
    public List<RegistroConsumo> getRegistrosConsumo() { return Collections.unmodifiableList(registrosConsumo); }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Nino that)) return false;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
