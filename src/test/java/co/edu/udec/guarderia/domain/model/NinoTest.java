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

    @BeforeEach
    void setUp() {
        nino = new Nino(
                new NinoId("MAT001"),
                new NombreCompleto("Juan Perez"),
                new FechaNacimiento(LocalDate.of(2020, 5, 15)),
                LocalDate.of(2024, 1, 10),
                new Direccion("Calle 123")
        );
    }

    @Test
    void debeCrearNinoConDatosBasicosYEstarActivo() {
        assertThat(nino.getId().value()).isEqualTo("MAT001");
        assertThat(nino.getNombre().value()).isEqualTo("Juan Perez");
        assertThat(nino.estaActivo()).isTrue();
    }

    @Test
    void debeDarDeBajaYNoPermitirDobleBaja() {
        nino.darDeBaja(LocalDate.of(2024, 6, 30));
        assertThat(nino.estaActivo()).isFalse();

        assertThatThrownBy(() -> nino.darDeBaja(LocalDate.of(2024, 7, 1)))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("ya fue dado de baja");
    }

    @Test
    void debeGestionarAlergias() {
        var mani = new NombreIngrediente("Mani");
        nino.registrarAlergia(mani);
        assertThat(nino.tieneAlergias()).isTrue();
        assertThat(nino.tieneAlergiaA(mani)).isTrue();

        assertThatThrownBy(() -> nino.registrarAlergia(mani))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("ya esta registrada");

        nino.eliminarAlergia(mani);
        assertThat(nino.tieneAlergias()).isFalse();
    }

    @Test
    void noDebePoderComerPlatoConAlergeno() {
        nino.registrarAlergia(new NombreIngrediente("Mani"));
        var plato = new Plato(new NombrePlato("Salsa de mani"),
                List.of(new NombreIngrediente("Mani"), new NombreIngrediente("Aceite")), BigDecimal.ONE);

        assertThat(nino.puedeComerPlato(plato)).isFalse();
    }

    @Test
    void debeAgregarPersonaAutorizadaYFiltrarFamiliares() {
        nino.agregarPersonaAutorizada(crearPersona("111A", TipoRelacion.MADRE));
        nino.agregarPersonaAutorizada(crearPersona("222B", TipoRelacion.CONOCIDO));
        nino.agregarPersonaAutorizada(crearPersona("333C", TipoRelacion.ABUELA));

        assertThat(nino.getPersonasAutorizadas()).hasSize(3);
        assertThat(nino.getPersonasAutorizadasFamiliares()).hasSize(2);
    }

    @Test
    void debeRegistrarConsumoYContarDiasComioEnMes() {
        nino.agregarRegistroConsumo(new RegistroConsumo(LocalDate.of(2024, 3, 1), new MenuId(1), true));
        nino.agregarRegistroConsumo(new RegistroConsumo(LocalDate.of(2024, 3, 5), new MenuId(2), true));
        nino.agregarRegistroConsumo(new RegistroConsumo(LocalDate.of(2024, 3, 10), new MenuId(1), false));
        nino.agregarRegistroConsumo(new RegistroConsumo(LocalDate.of(2024, 4, 1), new MenuId(1), true));

        assertThat(nino.getDiasComioEnMes(2024, 3)).isEqualTo(2);
        assertThat(nino.comioMenuEnFecha(new MenuId(1), LocalDate.of(2024, 3, 1))).isTrue();
        assertThat(nino.comioEnFecha(LocalDate.of(2024, 3, 10))).isFalse();
    }

    @Test
    void debeAsignarResponsablePagoYConsultarCuenta() {
        var responsable = new ResponsablePago(
                new DNI("12345678A"), new NombreCompleto("Carlos Perez"),
                new Direccion("Calle 123"), new Telefono("+573001234567"),
                new NumeroCuentaBancaria("ES1234567890")
        );
        nino.asignarResponsablePago(responsable);
        assertThat(nino.getResponsablePago().getNumeroCuenta().value()).isEqualTo("ES1234567890");
    }

    private PersonaAutorizada crearPersona(String dni, TipoRelacion relacion) {
        return new PersonaAutorizada(new DNI(dni), new NombreCompleto("Persona " + dni),
                new Direccion("Dir " + dni), List.of(new Telefono("+571234567")), relacion);
    }
}
