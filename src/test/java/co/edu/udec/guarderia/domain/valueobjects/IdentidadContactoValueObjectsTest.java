package co.edu.udec.guarderia.domain.valueobjects;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class IdentidadContactoValueObjectsTest {

    @Test
    void debeCrearDNIValido() {
        var vo = new DNI("12345678A");
        assertThat(vo.value()).isEqualTo("12345678A");
    }

    @Test
    void debeFallarConDNINulo() {
        assertThatThrownBy(() -> new DNI(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("DNI");
    }

    @Test
    void debeFallarConDNIVacio() {
        assertThatThrownBy(() -> new DNI(""))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void debeCrearTelefonoValido() {
        var vo = new Telefono("+573001234567");
        assertThat(vo.value()).isEqualTo("+573001234567");
    }

    @Test
    void debeFallarConTelefonoNulo() {
        assertThatThrownBy(() -> new Telefono(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("telefono");
    }

    @Test
    void debeFallarConTelefonoVacio() {
        assertThatThrownBy(() -> new Telefono(""))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void debeCrearNumeroCuentaBancariaValido() {
        var vo = new NumeroCuentaBancaria("ES12345678901234567890");
        assertThat(vo.value()).isEqualTo("ES12345678901234567890");
    }

    @Test
    void debeFallarConNumeroCuentaBancariaNulo() {
        assertThatThrownBy(() -> new NumeroCuentaBancaria(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("cuenta bancaria");
    }

    @Test
    void debeFallarConNumeroCuentaBancariaVacio() {
        assertThatThrownBy(() -> new NumeroCuentaBancaria(""))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
