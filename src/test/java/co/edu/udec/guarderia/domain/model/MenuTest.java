package co.edu.udec.guarderia.domain.model;

import co.edu.udec.guarderia.domain.valueobjects.MenuId;
import co.edu.udec.guarderia.domain.valueobjects.NombreIngrediente;
import co.edu.udec.guarderia.domain.valueobjects.NombrePlato;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class MenuTest {

    @Test
    void debeCrearMenuYAgregarPlatos() {
        var plato1 = new Plato(new NombrePlato("Arroz con pollo"),
                List.of(new NombreIngrediente("Arroz"), new NombreIngrediente("Pollo")), new BigDecimal("5.00"));
        var plato2 = new Plato(new NombrePlato("Sopa"),
                List.of(new NombreIngrediente("Zanahoria")), new BigDecimal("3.00"));

        var menu = new Menu(new MenuId(1), List.of(plato1));
        menu.agregarPlato(plato2);

        assertThat(menu.getPlatos()).hasSize(2);
        assertThat(menu.getPrecioTotal()).isEqualByComparingTo("8.00");
    }

    @Test
    void debeFallarAlAgregarPlatoDuplicado() {
        var plato = new Plato(new NombrePlato("Sopa"),
                List.of(new NombreIngrediente("Agua")), BigDecimal.ONE);
        var menu = new Menu(new MenuId(1), List.of(plato));

        assertThatThrownBy(() -> menu.agregarPlato(plato))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("ya existe");
    }

    @Test
    void debeEliminarPlatoYVerificarContenido() {
        var plato = new Plato(new NombrePlato("Sopa"),
                List.of(new NombreIngrediente("Zanahoria")), BigDecimal.ONE);
        var menu = new Menu(new MenuId(1), List.of(plato));

        menu.eliminarPlato(new NombrePlato("Sopa"));
        assertThat(menu.getPlatos()).isEmpty();

        assertThat(menu.contieneIngrediente(new NombreIngrediente("Zanahoria"))).isFalse();
    }
}
