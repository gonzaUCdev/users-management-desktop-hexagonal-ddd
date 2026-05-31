package co.edu.udec.guarderia.domain.model;

import co.edu.udec.guarderia.domain.enums.TipoRelacion;
import co.edu.udec.guarderia.domain.valueobjects.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class NinoTest {

    private Nino nino;
    private NinoId ninoId;
    private NombreCompleto nombre;
    private FechaNacimiento fechaNacimiento;
    private LocalDate fechaIngreso;
    private Direccion direccion;

    @BeforeEach
    void setUp() {
        ninoId = new NinoId("MAT001");
        nombre = new NombreCompleto("Juan Perez");
        fechaNacimiento = new FechaNacimiento(LocalDate.of(2020, 5, 15));
        fechaIngreso = LocalDate.of(2024, 1, 10);
        direccion = new Direccion("Calle 123 #45-67");
        nino = new Nino(ninoId, nombre, fechaNacimiento, fechaIngreso, direccion);
    }

    @Test
    void debeCrearNinoConDatosBasicos() {
        assertThat(nino.getId().value()).isEqualTo("MAT001");
        assertThat(nino.getNombre().value()).isEqualTo("Juan Perez");
        assertThat(nino.getFechaNacimiento().value()).isEqualTo(LocalDate.of(2020, 5, 15));
        assertThat(nino.getFechaIngreso()).isEqualTo(LocalDate.of(2024, 1, 10));
        assertThat(nino.getDireccion().value()).isEqualTo("Calle 123 #45-67");
    }

    @Test
    void ninoRecienCreadoEstaActivo() {
        assertThat(nino.estaActivo()).isTrue();
        assertThat(nino.getFechaBaja()).isNull();
    }

    @Test
    void debeFallarConFechaIngresoAntesDeNacimiento() {
        assertThatThrownBy(() -> new Nino(
                ninoId, nombre, fechaNacimiento,
                LocalDate.of(2019, 1, 1), direccion
        )).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("anterior a la fecha de nacimiento");
    }

    @Test
    void debeDarDeBajaCorrectamente() {
        var fechaBaja = LocalDate.of(2024, 6, 30);
        nino.darDeBaja(fechaBaja);

        assertThat(nino.estaActivo()).isFalse();
        assertThat(nino.getFechaBaja()).isEqualTo(fechaBaja);
    }

    @Test
    void debeFallarDarDeBajaConFechaAnteriorAIngreso() {
        var fechaInvalida = LocalDate.of(2023, 12, 1);
        assertThatThrownBy(() -> nino.darDeBaja(fechaInvalida))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("anterior a la fecha de ingreso");
    }

    @Test
    void debeFallarDarDeBajaDosVeces() {
        nino.darDeBaja(LocalDate.of(2024, 6, 30));
        assertThatThrownBy(() -> nino.darDeBaja(LocalDate.of(2024, 7, 1)))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("ya fue dado de baja");
    }

    @Test
    void debeRegistrarAlergia() {
        var ingrediente = new NombreIngrediente("Mani");
        nino.registrarAlergia(ingrediente);

        assertThat(nino.tieneAlergiaA(ingrediente)).isTrue();
        assertThat(nino.tieneAlergias()).isTrue();
    }

    @Test
    void debeFallarAlRegistrarAlergiaDuplicada() {
        var ingrediente = new NombreIngrediente("Mani");
        nino.registrarAlergia(ingrediente);

        assertThatThrownBy(() -> nino.registrarAlergia(ingrediente))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("ya esta registrada");
    }

    @Test
    void debeFallarAlRegistrarAlergiaNula() {
        assertThatThrownBy(() -> nino.registrarAlergia(null))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void debeEliminarAlergia() {
        var ingrediente = new NombreIngrediente("Mani");
        nino.registrarAlergia(ingrediente);
        nino.eliminarAlergia(ingrediente);

        assertThat(nino.tieneAlergiaA(ingrediente)).isFalse();
        assertThat(nino.tieneAlergias()).isFalse();
    }

    @Test
    void ninoSinAlergiasNoTieneAlergias() {
        assertThat(nino.tieneAlergias()).isFalse();
    }

    @Test
    void debePoderComerPlatoSinAlergenos() {
        var plato = new Plato(new NombrePlato("Arroz"),
                List.of(new NombreIngrediente("Arroz")), BigDecimal.ONE);

        assertThat(nino.puedeComerPlato(plato)).isTrue();
    }

    @Test
    void noDebePoderComerPlatoConAlergeno() {
        nino.registrarAlergia(new NombreIngrediente("Mani"));
        var plato = new Plato(new NombrePlato("Salsa de mani"),
                List.of(new NombreIngrediente("Mani"), new NombreIngrediente("Aceite")), BigDecimal.ONE);

        assertThat(nino.puedeComerPlato(plato)).isFalse();
    }

    @Test
    void debeAgregarPersonaAutorizada() {
        var persona = crearPersonaAutorizada("12345678A", TipoRelacion.MADRE);

        nino.agregarPersonaAutorizada(persona);

        assertThat(nino.getPersonasAutorizadas()).hasSize(1);
        assertThat(nino.getPersonasAutorizadas().get(0).getDni().value()).isEqualTo("12345678A");
    }

    @Test
    void debeFallarAgregarPersonaDuplicada() {
        var persona = crearPersonaAutorizada("12345678A", TipoRelacion.MADRE);
        nino.agregarPersonaAutorizada(persona);

        assertThatThrownBy(() -> nino.agregarPersonaAutorizada(persona))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Ya existe");
    }

    @Test
    void debeFiltrarPersonasAutorizadasFamiliares() {
        nino.agregarPersonaAutorizada(crearPersonaAutorizada("111A", TipoRelacion.MADRE));
        nino.agregarPersonaAutorizada(crearPersonaAutorizada("222B", TipoRelacion.CONOCIDO));
        nino.agregarPersonaAutorizada(crearPersonaAutorizada("333C", TipoRelacion.ABUELA));

        var familiares = nino.getPersonasAutorizadasFamiliares();
        assertThat(familiares).hasSize(2);
        assertThat(familiares).allMatch(p -> p.getRelacion().esFamiliar());
    }

    @Test
    void debeEliminarPersonaAutorizada() {
        nino.agregarPersonaAutorizada(crearPersonaAutorizada("111A", TipoRelacion.MADRE));
        nino.agregarPersonaAutorizada(crearPersonaAutorizada("222B", TipoRelacion.PADRE));

        nino.eliminarPersonaAutorizada(new DNI("111A"));

        assertThat(nino.getPersonasAutorizadas()).hasSize(1);
        assertThat(nino.getPersonasAutorizadas().get(0).getDni().value()).isEqualTo("222B");
    }

    @Test
    void debeAsignarResponsablePago() {
        var responsable = new ResponsablePago(
                new DNI("12345678A"), new NombreCompleto("Carlos Perez"),
                new Direccion("Calle 123"), new Telefono("+573001234567"),
                new NumeroCuentaBancaria("ES1234567890")
        );

        nino.asignarResponsablePago(responsable);

        assertThat(nino.getResponsablePago()).isNotNull();
        assertThat(nino.getResponsablePago().getDni().value()).isEqualTo("12345678A");
    }

    @Test
    void debeAgregarRegistroConsumo() {
        var registro = new RegistroConsumo(LocalDate.of(2024, 3, 15), new MenuId(1), true);
        nino.agregarRegistroConsumo(registro);

        assertThat(nino.getRegistrosConsumo()).hasSize(1);
        assertThat(nino.comioEnFecha(LocalDate.of(2024, 3, 15))).isTrue();
    }

    @Test
    void debeFallarRegistroConsumoDuplicado() {
        var fecha = LocalDate.of(2024, 3, 15);
        nino.agregarRegistroConsumo(new RegistroConsumo(fecha, new MenuId(1), true));

        assertThatThrownBy(() -> nino.agregarRegistroConsumo(new RegistroConsumo(fecha, new MenuId(1), false)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Ya existe");
    }

    @Test
    void debeContarDiasComioEnMes() {
        nino.agregarRegistroConsumo(new RegistroConsumo(LocalDate.of(2024, 3, 1), new MenuId(1), true));
        nino.agregarRegistroConsumo(new RegistroConsumo(LocalDate.of(2024, 3, 5), new MenuId(2), true));
        nino.agregarRegistroConsumo(new RegistroConsumo(LocalDate.of(2024, 3, 10), new MenuId(1), false));
        nino.agregarRegistroConsumo(new RegistroConsumo(LocalDate.of(2024, 4, 1), new MenuId(1), true));

        var diasMarzo = nino.getDiasComioEnMes(2024, 3);
        assertThat(diasMarzo).isEqualTo(2);
    }

    @Test
    void debeVerificarComioMenuEnFecha() {
        var fecha = LocalDate.of(2024, 3, 15);
        nino.agregarRegistroConsumo(new RegistroConsumo(fecha, new MenuId(1), true));

        assertThat(nino.comioMenuEnFecha(new MenuId(1), fecha)).isTrue();
        assertThat(nino.comioMenuEnFecha(new MenuId(2), fecha)).isFalse();
    }

    @Test
    void noDebeHaberComidoSiRegistroTieneComioFalse() {
        var fecha = LocalDate.of(2024, 3, 15);
        nino.agregarRegistroConsumo(new RegistroConsumo(fecha, new MenuId(1), false));

        assertThat(nino.comioEnFecha(fecha)).isFalse();
    }

    @Test
    void ninoSinConsumosNoHaComido() {
        assertThat(nino.comioEnFecha(LocalDate.of(2024, 3, 15))).isFalse();
    }

    @Test
    void debeFallarConConstructorParametrosNulos() {
        assertThatThrownBy(() -> new Nino(null, nombre, fechaNacimiento, fechaIngreso, direccion))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> new Nino(ninoId, null, fechaNacimiento, fechaIngreso, direccion))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> new Nino(ninoId, nombre, null, fechaIngreso, direccion))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> new Nino(ninoId, nombre, fechaNacimiento, null, direccion))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> new Nino(ninoId, nombre, fechaNacimiento, fechaIngreso, null))
                .isInstanceOf(IllegalArgumentException.class);
    }

    private PersonaAutorizada crearPersonaAutorizada(String dni, TipoRelacion relacion) {
        return new PersonaAutorizada(
                new DNI(dni), new NombreCompleto("Persona " + dni),
                new Direccion("Direccion " + dni),
                List.of(new Telefono("+571234567")),
                relacion
        );
    }
}
