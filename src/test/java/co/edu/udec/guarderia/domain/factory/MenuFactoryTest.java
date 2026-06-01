package co.edu.udec.guarderia.domain.factory;

import co.edu.udec.guarderia.domain.model.Menu;
import co.edu.udec.guarderia.domain.model.Plato;
import co.edu.udec.guarderia.domain.valueobjects.NombreIngrediente;
import co.edu.udec.guarderia.domain.valueobjects.NombrePlato;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class MenuFactoryTest {

    @Test
    void debeCrearMenuConIdEntero() {
        var platos = List.of(new Plato(new NombrePlato("Sopa"),
                List.of(new NombreIngrediente("Agua")), BigDecimal.ONE));
        var menu = MenuFactory.crearMenu(1, platos);

        assertThat(menu.getId().value()).isEqualTo(1);
        assertThat(menu.getPlatos()).hasSize(1);
    }

    @Test
    void debeFallarConIdNulo() {
        var platos = List.of(new Plato(new NombrePlato("Sopa"),
                List.of(new NombreIngrediente("Agua")), BigDecimal.ONE));

        assertThatThrownBy(() -> MenuFactory.crearMenu((Integer) null, platos))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("ID del menu");
    }

    @Test
    void debeFallarConIdCero() {
        var platos = List.of(new Plato(new NombrePlato("Sopa"),
                List.of(new NombreIngrediente("Agua")), BigDecimal.ONE));

        assertThatThrownBy(() -> MenuFactory.crearMenu(0, platos))
                .isInstanceOf(RuntimeException.class);
    }

    @Test
    void debeFallarConPlatosVacios() {
        assertThatThrownBy(() -> MenuFactory.crearMenu(1, List.of()))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("al menos un plato");
    }

    @Test
    void debeFallarConPlatosNulos() {
        assertThatThrownBy(() -> MenuFactory.crearMenu(1, null))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("al menos un plato");
    }
}
