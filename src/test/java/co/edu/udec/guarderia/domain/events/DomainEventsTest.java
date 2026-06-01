package co.edu.udec.guarderia.domain.events;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.*;

class DomainEventsTest {

    @Test
    void debeCrearNinoRegistradoEvent() {
        var event = new NinoRegistradoEvent("MAT001", "Juan Perez");

        assertThat(event.ninoId()).isEqualTo("MAT001");
        assertThat(event.nombre()).isEqualTo("Juan Perez");
        assertThat(event.fecha()).isNotNull();
    }

    @Test
    void debeCrearNinoDadoDeBajaEvent() {
        var fechaBaja = LocalDate.of(2024, 6, 30);
        var event = new NinoDadoDeBajaEvent("MAT001", fechaBaja);

        assertThat(event.ninoId()).isEqualTo("MAT001");
        assertThat(event.fechaBaja()).isEqualTo(fechaBaja);
        assertThat(event.fecha()).isNotNull();
    }

    @Test
    void debeCrearAlergiaRegistradaEvent() {
        var event = new AlergiaRegistradaEvent("MAT001", "Mani");

        assertThat(event.ninoId()).isEqualTo("MAT001");
        assertThat(event.ingrediente()).isEqualTo("Mani");
        assertThat(event.fecha()).isNotNull();
    }

    @Test
    void debeCrearConsumoRegistradoEvent() {
        var fecha = LocalDate.of(2024, 3, 15);
        var event = new ConsumoRegistradoEvent("MAT001", 1, fecha, true);

        assertThat(event.ninoId()).isEqualTo("MAT001");
        assertThat(event.menuId()).isEqualTo(1);
        assertThat(event.fechaConsumo()).isEqualTo(fecha);
        assertThat(event.comio()).isTrue();
        assertThat(event.fecha()).isNotNull();
    }

    @Test
    void debeCrearMenuCreadoEvent() {
        var event = new MenuCreadoEvent(1);

        assertThat(event.menuId()).isEqualTo(1);
        assertThat(event.fecha()).isNotNull();
    }
}
