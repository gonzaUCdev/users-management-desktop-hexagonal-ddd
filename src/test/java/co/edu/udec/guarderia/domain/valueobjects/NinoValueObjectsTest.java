package co.edu.udec.guarderia.domain.valueobjects;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.*;

class NinoValueObjectsTest {

    @Test
    void debeCrearNinoIdValido() {
        var vo = new NinoId("MAT001");
        assertThat(vo.value()).isEqualTo("MAT001");
    }

    @Test
    void debeFallarConNinoIdNulo() {
        assertThatThrownBy(() -> new NinoId(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("matricula");
    }

    @Test
    void debeFallarConNinoIdVacio() {
        assertThatThrownBy(() -> new NinoId(""))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("matricula");
    }

    @Test
    void debeFallarConNinoIdBlanco() {
        assertThatThrownBy(() -> new NinoId("   "))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("matricula");
    }

    @Test
    void debeCrearNombreCompletoValido() {
        var vo = new NombreCompleto("Juan Perez");
        assertThat(vo.value()).isEqualTo("Juan Perez");
    }

    @Test
    void debeFallarConNombreCompletoNulo() {
        assertThatThrownBy(() -> new NombreCompleto(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("nombre");
    }

    @Test
    void debeFallarConNombreCompletoVacio() {
        assertThatThrownBy(() -> new NombreCompleto(""))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void debeCrearFechaNacimientoValida() {
        var fecha = LocalDate.of(2020, 5, 15);
        var vo = new FechaNacimiento(fecha);
        assertThat(vo.value()).isEqualTo(fecha);
    }

    @Test
    void debeFallarConFechaNacimientoNula() {
        assertThatThrownBy(() -> new FechaNacimiento(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("fecha de nacimiento");
    }

    @Test
    void debeFallarConFechaNacimientoFutura() {
        var fechaFutura = LocalDate.now().plusDays(1);
        assertThatThrownBy(() -> new FechaNacimiento(fechaFutura))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("futura");
    }

    @Test
    void debeAceptarFechaNacimientoHoy() {
        var hoy = LocalDate.now();
        var vo = new FechaNacimiento(hoy);
        assertThat(vo.value()).isEqualTo(hoy);
    }

    @Test
    void debeCrearDireccionValida() {
        var vo = new Direccion("Calle 123 #45-67");
        assertThat(vo.value()).isEqualTo("Calle 123 #45-67");
    }

    @Test
    void debeFallarConDireccionNula() {
        assertThatThrownBy(() -> new Direccion(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("direccion");
    }

    @Test
    void debeFallarConDireccionVacia() {
        assertThatThrownBy(() -> new Direccion(""))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
