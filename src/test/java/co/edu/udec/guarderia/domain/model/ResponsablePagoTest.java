package co.edu.udec.guarderia.domain.model;

import co.edu.udec.guarderia.domain.valueobjects.*;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class ResponsablePagoTest {

    @Test
    void debeCrearResponsablePagoValido() {
        var responsable = new ResponsablePago(
                new DNI("12345678A"),
                new NombreCompleto("Carlos Perez"),
                new Direccion("Calle 123"),
                new Telefono("+573001234567"),
                new NumeroCuentaBancaria("ES1234567890")
        );

        assertThat(responsable.getDni().value()).isEqualTo("12345678A");
        assertThat(responsable.getNombre().value()).isEqualTo("Carlos Perez");
        assertThat(responsable.getNumeroCuenta().value()).isEqualTo("ES1234567890");
    }

    @Test
    void debeFallarConDniNulo() {
        assertThatThrownBy(() -> new ResponsablePago(
                null, new NombreCompleto("Carlos"), new Direccion("Calle 123"),
                new Telefono("123"), new NumeroCuentaBancaria("ES12")
        )).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void debeFallarConNumeroCuentaNulo() {
        assertThatThrownBy(() -> new ResponsablePago(
                new DNI("12345678A"), new NombreCompleto("Carlos"), new Direccion("Calle 123"),
                new Telefono("123"), null
        )).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void responsablesConMismoDniSonIguales() {
        var dni = new DNI("12345678A");
        var r1 = new ResponsablePago(dni, new NombreCompleto("A"), new Direccion("D1"),
                new Telefono("111"), new NumeroCuentaBancaria("ES1"));
        var r2 = new ResponsablePago(dni, new NombreCompleto("B"), new Direccion("D2"),
                new Telefono("222"), new NumeroCuentaBancaria("ES2"));

        assertThat(r1).isEqualTo(r2);
    }
}
