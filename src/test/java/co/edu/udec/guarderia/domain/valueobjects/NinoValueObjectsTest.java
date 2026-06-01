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
    void debeFallarConNinoIdNuloOVacio() {
        assertThatThrownBy(() -> new NinoId(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("matricula");
        assertThatThrownBy(() -> new NinoId(""))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void debeCrearFechaNacimientoValida() {
        var fecha = LocalDate.of(2020, 5, 15);
        var vo = new FechaNacimiento(fecha);
        assertThat(vo.value()).isEqualTo(fecha);
    }

    @Test
    void debeFallarConFechaNacimientoNulaOFutura() {
        assertThatThrownBy(() -> new FechaNacimiento(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("fecha de nacimiento");
        assertThatThrownBy(() -> new FechaNacimiento(LocalDate.now().plusDays(1)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("futura");
    }
}
