package co.edu.udec.guarderia.domain.factory;

import co.edu.udec.guarderia.domain.model.Nino;
import co.edu.udec.guarderia.domain.model.PersonaAutorizada;
import co.edu.udec.guarderia.domain.model.RegistroConsumo;
import co.edu.udec.guarderia.domain.model.ResponsablePago;
import co.edu.udec.guarderia.domain.valueobjects.Direccion;
import co.edu.udec.guarderia.domain.valueobjects.FechaNacimiento;
import co.edu.udec.guarderia.domain.valueobjects.NinoId;
import co.edu.udec.guarderia.domain.valueobjects.NombreCompleto;
import co.edu.udec.guarderia.domain.valueobjects.NombreIngrediente;

import java.time.LocalDate;

public class NinoBuilder {
    private NinoId id;
    private NombreCompleto nombre;
    private FechaNacimiento fechaNacimiento;
    private LocalDate fechaIngreso;
    private Direccion direccion;

    public NinoBuilder conId(NinoId id) {
        this.id = id;
        return this;
    }

    public NinoBuilder conNombre(NombreCompleto nombre) {
        this.nombre = nombre;
        return this;
    }

    public NinoBuilder conFechaNacimiento(FechaNacimiento fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
        return this;
    }

    public NinoBuilder conFechaIngreso(LocalDate fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
        return this;
    }

    public NinoBuilder conDireccion(Direccion direccion) {
        this.direccion = direccion;
        return this;
    }

    public Nino build() {
        Nino nino = new Nino(id, nombre, fechaNacimiento, fechaIngreso, direccion);
        return nino;
    }

    public static Nino construirConDatosBasicos(NinoId id, NombreCompleto nombre,
                                                  FechaNacimiento fechaNacimiento,
                                                  LocalDate fechaIngreso, Direccion direccion) {
        return new Nino(id, nombre, fechaNacimiento, fechaIngreso, direccion);
    }
}
