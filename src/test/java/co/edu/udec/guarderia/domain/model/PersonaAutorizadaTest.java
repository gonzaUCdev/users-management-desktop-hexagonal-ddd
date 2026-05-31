package co.edu.udec.guarderia.domain.model;

import co.edu.udec.guarderia.domain.enums.TipoRelacion;
import co.edu.udec.guarderia.domain.valueobjects.DNI;
import co.edu.udec.guarderia.domain.valueobjects.Direccion;
import co.edu.udec.guarderia.domain.valueobjects.NombreCompleto;
import co.edu.udec.guarderia.domain.valueobjects.NumeroCuentaBancaria;
import co.edu.udec.guarderia.domain.valueobjects.Telefono;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class PersonaAutorizadaTest {

    @Test
    void debeCrearPersonaAutorizadaValida() {
        var persona = new PersonaAutorizada(
                new DNI("12345678A"),
                new NombreCompleto("Maria Perez"),
                new Direccion("Calle 123"),
                List.of(new Telefono("+573001234567")),
                TipoRelacion.MADRE
        );

        assertThat(persona.getDni().value()).isEqualTo("12345678A");
        assertThat(persona.getNombre().value()).isEqualTo("Maria Perez");
        assertThat(persona.getRelacion()).isEqualTo(TipoRelacion.MADRE);
        assertThat(persona.getTelefonos()).hasSize(1);
    }

    @Test
    void debeCrearPersonaConMultiplesTelefonos() {
        var persona = new PersonaAutorizada(
                new DNI("12345678A"),
                new NombreCompleto("Maria Perez"),
                new Direccion("Calle 123"),
                List.of(new Telefono("+573001234567"), new Telefono("+573007654321")),
                TipoRelacion.MADRE
        );

        assertThat(persona.getTelefonos()).hasSize(2);
    }

    @Test
    void debeFallarConTelefonosVacios() {
        assertThatThrownBy(() -> new PersonaAutorizada(
                new DNI("12345678A"),
                new NombreCompleto("Maria Perez"),
                new Direccion("Calle 123"),
                List.of(),
                TipoRelacion.MADRE
        )).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("al menos un telefono");
    }

    @Test
    void debeFallarConDniNulo() {
        assertThatThrownBy(() -> new PersonaAutorizada(
                null, new NombreCompleto("Maria"), new Direccion("Calle 123"),
                List.of(new Telefono("123")), TipoRelacion.MADRE
        )).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void debeFallarConRelacionNula() {
        assertThatThrownBy(() -> new PersonaAutorizada(
                new DNI("12345678A"), new NombreCompleto("Maria"), new Direccion("Calle 123"),
                List.of(new Telefono("123")), null
        )).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void personasConMismoDniSonIguales() {
        var dni = new DNI("12345678A");
        var p1 = new PersonaAutorizada(dni, new NombreCompleto("A"), new Direccion("D1"),
                List.of(new Telefono("111")), TipoRelacion.MADRE);
        var p2 = new PersonaAutorizada(dni, new NombreCompleto("B"), new Direccion("D2"),
                List.of(new Telefono("222")), TipoRelacion.PADRE);

        assertThat(p1).isEqualTo(p2);
    }

    @Test
    void telefonosDebenSerInmutables() {
        var persona = new PersonaAutorizada(
                new DNI("12345678A"), new NombreCompleto("Maria"), new Direccion("Calle 123"),
                List.of(new Telefono("123")), TipoRelacion.MADRE
        );

        assertThatThrownBy(() -> persona.getTelefonos().add(new Telefono("456")))
                .isInstanceOf(UnsupportedOperationException.class);
    }
}
