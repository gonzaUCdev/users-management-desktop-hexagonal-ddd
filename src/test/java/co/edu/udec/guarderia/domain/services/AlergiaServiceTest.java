package co.edu.udec.guarderia.domain.services;

import co.edu.udec.guarderia.domain.model.Menu;
import co.edu.udec.guarderia.domain.model.Nino;
import co.edu.udec.guarderia.domain.model.Plato;
import co.edu.udec.guarderia.domain.valueobjects.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class AlergiaServiceTest {

    private AlergiaService service;
    private Nino nino;
    private Menu menu;

    @BeforeEach
    void setUp() {
        service = new AlergiaService();
        nino = new Nino(
                new NinoId("MAT001"), new NombreCompleto("Juan Perez"),
                new FechaNacimiento(LocalDate.of(2020, 5, 15)),
                LocalDate.of(2024, 1, 10), new Direccion("Calle 123")
        );

        var plato1 = new Plato(new NombrePlato("Arroz con pollo"),
                List.of(new NombreIngrediente("Arroz"), new NombreIngrediente("Pollo")),
                new BigDecimal("5.00"));
        var plato2 = new Plato(new NombrePlato("Salsa de mani"),
                List.of(new NombreIngrediente("Mani"), new NombreIngrediente("Aceite")),
                new BigDecimal("3.00"));
        menu = new Menu(new MenuId(1), List.of(plato1, plato2));
    }

    @Test
    void ninoSinAlergiasPuedeComerTodoElMenu() {
        assertThat(service.puedeComerMenu(nino, menu)).isTrue();
        assertThat(service.obtenerPlatosNoAptos(nino, menu)).isEmpty();
    }

    @Test
    void debeIdentificarPlatosNoAptos() {
        nino.registrarAlergia(new NombreIngrediente("Mani"));

        var noAptos = service.obtenerPlatosNoAptos(nino, menu);
        assertThat(noAptos).hasSize(1);
        assertThat(noAptos.get(0).getNombre().value()).isEqualTo("Salsa de mani");
    }

    @Test
    void noPuedeComerMenuConAlergeno() {
        nino.registrarAlergia(new NombreIngrediente("Mani"));

        assertThat(service.puedeComerMenu(nino, menu)).isFalse();
    }

    @Test
    void debeFallarVerificacionDeAlergia() {
        nino.registrarAlergia(new NombreIngrediente("Mani"));
        var platoAlergenico = menu.getPlatos().get(1);

        assertThatThrownBy(() -> service.verificarAlergia(nino, platoAlergenico))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("alergico a 'Mani'");
    }

    @Test
    void verificacionExitosaSinAlergias() {
        var platoSeguro = menu.getPlatos().get(0);

        assertThatCode(() -> service.verificarAlergia(nino, platoSeguro))
                .doesNotThrowAnyException();
    }

    @Test
    void debeFallarConNinoNulo() {
        assertThatThrownBy(() -> service.obtenerPlatosNoAptos(null, menu))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void debeFallarConMenuNulo() {
        assertThatThrownBy(() -> service.obtenerPlatosNoAptos(nino, null))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
