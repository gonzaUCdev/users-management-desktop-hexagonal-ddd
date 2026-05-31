package co.edu.udec.guarderia.domain.model;

import co.edu.udec.guarderia.domain.valueobjects.MenuId;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.*;

class RegistroConsumoTest {

    @Test
    void debeCrearRegistroConsumoValido() {
        var registro = new RegistroConsumo(LocalDate.of(2024, 3, 15), new MenuId(1), true);

        assertThat(registro.getFecha()).isEqualTo(LocalDate.of(2024, 3, 15));
        assertThat(registro.getMenuId().value()).isEqualTo(1);
        assertThat(registro.isComio()).isTrue();
    }

    @Test
    void debeCrearRegistroDondeNoComio() {
        var registro = new RegistroConsumo(LocalDate.of(2024, 3, 15), new MenuId(1), false);

        assertThat(registro.isComio()).isFalse();
    }

    @Test
    void debeFallarConFechaNula() {
        assertThatThrownBy(() -> new RegistroConsumo(null, new MenuId(1), true))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("fecha");
    }

    @Test
    void debeFallarConMenuIdNulo() {
        assertThatThrownBy(() -> new RegistroConsumo(LocalDate.now(), null, true))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("menu");
    }

    @Test
    void debeIdentificarMismoMesYAnio() {
        var registro = new RegistroConsumo(LocalDate.of(2024, 3, 15), new MenuId(1), true);

        assertThat(registro.ocurrioEnMes(2024, 3)).isTrue();
    }

    @Test
    void debeIdentificarDistintoMes() {
        var registro = new RegistroConsumo(LocalDate.of(2024, 3, 15), new MenuId(1), true);

        assertThat(registro.ocurrioEnMes(2024, 4)).isFalse();
    }

    @Test
    void debeIdentificarDistintoAnio() {
        var registro = new RegistroConsumo(LocalDate.of(2024, 3, 15), new MenuId(1), true);

        assertThat(registro.ocurrioEnMes(2023, 3)).isFalse();
    }

    @Test
    void registrosConMismaFechaYMenuSonIguales() {
        var fecha = LocalDate.of(2024, 3, 15);
        var r1 = new RegistroConsumo(fecha, new MenuId(1), true);
        var r2 = new RegistroConsumo(fecha, new MenuId(1), false);

        assertThat(r1).isEqualTo(r2);
    }

    @Test
    void registrosConDistintoMenuNoSonIguales() {
        var fecha = LocalDate.of(2024, 3, 15);
        var r1 = new RegistroConsumo(fecha, new MenuId(1), true);
        var r2 = new RegistroConsumo(fecha, new MenuId(2), true);

        assertThat(r1).isNotEqualTo(r2);
    }
}
