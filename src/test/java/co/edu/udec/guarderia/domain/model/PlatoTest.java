package co.edu.udec.guarderia.domain.model;

import co.edu.udec.guarderia.domain.valueobjects.NombreIngrediente;
import co.edu.udec.guarderia.domain.valueobjects.NombrePlato;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class PlatoTest {

    @Test
    void debeCrearPlatoValido() {
        var nombre = new NombrePlato("Arroz con pollo");
        var ingredientes = List.of(new NombreIngrediente("Arroz"), new NombreIngrediente("Pollo"));
        var precio = new BigDecimal("5.00");

        var plato = new Plato(nombre, ingredientes, precio);

        assertThat(plato.getNombre().value()).isEqualTo("Arroz con pollo");
        assertThat(plato.getIngredientes()).hasSize(2);
        assertThat(plato.getPrecio()).isEqualByComparingTo("5.00");
    }

    @Test
    void debeFallarConNombreNulo() {
        var ingredientes = List.of(new NombreIngrediente("Arroz"));
        assertThatThrownBy(() -> new Plato(null, ingredientes, BigDecimal.ONE))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("nombre del plato");
    }

    @Test
    void debeFallarConIngredientesVacios() {
        var nombre = new NombrePlato("Sopa");
        assertThatThrownBy(() -> new Plato(nombre, List.of(), BigDecimal.ONE))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("al menos un ingrediente");
    }

    @Test
    void debeFallarConIngredientesNulos() {
        var nombre = new NombrePlato("Sopa");
        assertThatThrownBy(() -> new Plato(nombre, null, BigDecimal.ONE))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void debeFallarConPrecioNegativo() {
        var nombre = new NombrePlato("Sopa");
        var ingredientes = List.of(new NombreIngrediente("Agua"));
        assertThatThrownBy(() -> new Plato(nombre, ingredientes, new BigDecimal("-1.00")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("precio");
    }

    @Test
    void debeFallarConPrecioNulo() {
        var nombre = new NombrePlato("Sopa");
        var ingredientes = List.of(new NombreIngrediente("Agua"));
        assertThatThrownBy(() -> new Plato(nombre, ingredientes, null))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void debeAceptarPrecioCero() {
        var nombre = new NombrePlato("Agua");
        var ingredientes = List.of(new NombreIngrediente("Agua"));
        var plato = new Plato(nombre, ingredientes, BigDecimal.ZERO);
        assertThat(plato.getPrecio()).isEqualByComparingTo(BigDecimal.ZERO);
    }

    @Test
    void debeContenerIngrediente() {
        var arroz = new NombreIngrediente("Arroz");
        var pollo = new NombreIngrediente("Pollo");
        var plato = new Plato(new NombrePlato("Arroz con pollo"), List.of(arroz, pollo), BigDecimal.ONE);

        assertThat(plato.contieneIngrediente(arroz)).isTrue();
        assertThat(plato.contieneIngrediente(pollo)).isTrue();
    }

    @Test
    void noDebeContenerIngredienteNoIncluido() {
        var arroz = new NombreIngrediente("Arroz");
        var mani = new NombreIngrediente("Mani");
        var plato = new Plato(new NombrePlato("Arroz blanco"), List.of(arroz), BigDecimal.ONE);

        assertThat(plato.contieneIngrediente(mani)).isFalse();
    }

    @Test
    void platosConMismoNombreSonIguales() {
        var nombre = new NombrePlato("Sopa");
        var ing1 = List.of(new NombreIngrediente("Agua"));
        var ing2 = List.of(new NombreIngrediente("Caldo"));

        var plato1 = new Plato(nombre, ing1, BigDecimal.ONE);
        var plato2 = new Plato(nombre, ing2, new BigDecimal("2.00"));

        assertThat(plato1).isEqualTo(plato2);
    }
}
