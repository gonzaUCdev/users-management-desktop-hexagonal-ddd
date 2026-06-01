package co.edu.udec.guarderia.domain.valueobjects;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class MenuValueObjectsTest {

    @Test
    void debeCrearMenuIdValido() {
        var vo = new MenuId(1);
        assertThat(vo.value()).isEqualTo(1);
    }

    @Test
    void debeFallarConMenuIdNulo() {
        assertThatThrownBy(() -> new MenuId(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("numero positivo");
    }

    @Test
    void debeFallarConMenuIdCero() {
        assertThatThrownBy(() -> new MenuId(0))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("numero positivo");
    }

    @Test
    void debeFallarConMenuIdNegativo() {
        assertThatThrownBy(() -> new MenuId(-5))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("numero positivo");
    }

    @Test
    void debeCrearNombrePlatoValido() {
        var vo = new NombrePlato("Arroz con pollo");
        assertThat(vo.value()).isEqualTo("Arroz con pollo");
    }

    @Test
    void debeFallarConNombrePlatoNulo() {
        assertThatThrownBy(() -> new NombrePlato(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("plato");
    }

    @Test
    void debeFallarConNombrePlatoVacio() {
        assertThatThrownBy(() -> new NombrePlato(""))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void debeCrearNombreIngredienteValido() {
        var vo = new NombreIngrediente("Pollo");
        assertThat(vo.value()).isEqualTo("Pollo");
    }

    @Test
    void debeFallarConNombreIngredienteNulo() {
        assertThatThrownBy(() -> new NombreIngrediente(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("ingrediente");
    }

    @Test
    void debeFallarConNombreIngredienteVacio() {
        assertThatThrownBy(() -> new NombreIngrediente(""))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
