package co.edu.udec.guarderia.domain.model;

import co.edu.udec.guarderia.domain.valueobjects.*;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class MenuTest {

    private List<Plato> crearPlatosBasicos() {
        return List.of(
                new Plato(new NombrePlato("Arroz con pollo"),
                        List.of(new NombreIngrediente("Arroz"), new NombreIngrediente("Pollo")),
                        new BigDecimal("5.00")),
                new Plato(new NombrePlato("Sopa de verduras"),
                        List.of(new NombreIngrediente("Zanahoria"), new NombreIngrediente("Papa")),
                        new BigDecimal("3.00"))
        );
    }

    @Test
    void debeCrearMenuValido() {
        var platos = crearPlatosBasicos();
        var menu = new Menu(new MenuId(1), platos);

        assertThat(menu.getId().value()).isEqualTo(1);
        assertThat(menu.getPlatos()).hasSize(2);
    }

    @Test
    void debeFallarConIdNulo() {
        assertThatThrownBy(() -> new Menu(null, crearPlatosBasicos()))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("ID del menu");
    }

    @Test
    void debeAgregarPlatoNuevo() {
        var menu = new Menu(new MenuId(1), crearPlatosBasicos());
        var nuevoPlato = new Plato(new NombrePlato("Postre"),
                List.of(new NombreIngrediente("Fresa")), new BigDecimal("2.00"));

        menu.agregarPlato(nuevoPlato);

        assertThat(menu.getPlatos()).hasSize(3);
    }

    @Test
    void debeFallarAlAgregarPlatoDuplicado() {
        var platos = crearPlatosBasicos();
        var menu = new Menu(new MenuId(1), platos);

        assertThatThrownBy(() -> menu.agregarPlato(platos.get(0)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("ya existe");
    }

    @Test
    void debeFallarAlAgregarPlatoNulo() {
        var menu = new Menu(new MenuId(1), crearPlatosBasicos());

        assertThatThrownBy(() -> menu.agregarPlato(null))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void debeEliminarPlatoExistente() {
        var menu = new Menu(new MenuId(1), crearPlatosBasicos());
        var nombreAEliminar = new NombrePlato("Arroz con pollo");

        menu.eliminarPlato(nombreAEliminar);

        assertThat(menu.getPlatos()).hasSize(1);
        assertThat(menu.contienePlato(nombreAEliminar)).isFalse();
    }

    @Test
    void debeContenerPlato() {
        var menu = new Menu(new MenuId(1), crearPlatosBasicos());

        assertThat(menu.contienePlato(new NombrePlato("Arroz con pollo"))).isTrue();
        assertThat(menu.contienePlato(new NombrePlato("Pizza"))).isFalse();
    }

    @Test
    void debeContenerIngrediente() {
        var menu = new Menu(new MenuId(1), crearPlatosBasicos());

        assertThat(menu.contieneIngrediente(new NombreIngrediente("Pollo"))).isTrue();
        assertThat(menu.contieneIngrediente(new NombreIngrediente("Mani"))).isFalse();
    }

    @Test
    void debeCalcularPrecioTotal() {
        var menu = new Menu(new MenuId(1), crearPlatosBasicos());

        assertThat(menu.getPrecioTotal()).isEqualByComparingTo("8.00");
    }

    @Test
    void menusConMismoIdSonIguales() {
        var menu1 = new Menu(new MenuId(1), crearPlatosBasicos());
        var menu2 = new Menu(new MenuId(1), List.of());

        assertThat(menu1).isEqualTo(menu2);
    }
}
