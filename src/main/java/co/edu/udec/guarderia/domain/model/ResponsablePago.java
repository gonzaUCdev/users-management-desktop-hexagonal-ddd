package co.edu.udec.guarderia.domain.model;

import co.edu.udec.guarderia.domain.valueobjects.DNI;
import co.edu.udec.guarderia.domain.valueobjects.Direccion;
import co.edu.udec.guarderia.domain.valueobjects.NombreCompleto;
import co.edu.udec.guarderia.domain.valueobjects.NumeroCuentaBancaria;
import co.edu.udec.guarderia.domain.valueobjects.Telefono;

import java.util.Objects;

public class ResponsablePago {
    private final DNI dni;
    private final NombreCompleto nombre;
    private final Direccion direccion;
    private final Telefono telefono;
    private final NumeroCuentaBancaria numeroCuenta;

    public ResponsablePago(DNI dni, NombreCompleto nombre, Direccion direccion,
                           Telefono telefono, NumeroCuentaBancaria numeroCuenta) {
        if (dni == null) throw new IllegalArgumentException("El DNI del responsable de pago no puede ser nulo");
        if (nombre == null) throw new IllegalArgumentException("El nombre del responsable de pago no puede ser nulo");
        if (direccion == null) throw new IllegalArgumentException("La direccion del responsable de pago no puede ser nula");
        if (telefono == null) throw new IllegalArgumentException("El telefono del responsable de pago no puede ser nulo");
        if (numeroCuenta == null) throw new IllegalArgumentException("El numero de cuenta bancaria no puede ser nulo");

        this.dni = dni;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.numeroCuenta = numeroCuenta;
    }

    public DNI getDni() { return dni; }
    public NombreCompleto getNombre() { return nombre; }
    public Direccion getDireccion() { return direccion; }
    public Telefono getTelefono() { return telefono; }
    public NumeroCuentaBancaria getNumeroCuenta() { return numeroCuenta; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ResponsablePago that)) return false;
        return dni.equals(that.dni);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dni);
    }
}
