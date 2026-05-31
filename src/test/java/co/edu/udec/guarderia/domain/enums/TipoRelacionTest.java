package co.edu.udec.guarderia.domain.enums;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class TipoRelacionTest {

    @Test
    void debeIdentificarMadreComoFamiliar() {
        assertThat(TipoRelacion.MADRE.esFamiliar()).isTrue();
    }

    @Test
    void debeIdentificarPadreComoFamiliar() {
        assertThat(TipoRelacion.PADRE.esFamiliar()).isTrue();
    }

    @Test
    void debeIdentificarAbueloComoFamiliar() {
        assertThat(TipoRelacion.ABUELO.esFamiliar()).isTrue();
    }

    @Test
    void debeIdentificarAbuelaComoFamiliar() {
        assertThat(TipoRelacion.ABUELA.esFamiliar()).isTrue();
    }

    @Test
    void debeIdentificarTioComoFamiliar() {
        assertThat(TipoRelacion.TIO.esFamiliar()).isTrue();
    }

    @Test
    void debeIdentificarTiaComoFamiliar() {
        assertThat(TipoRelacion.TIA.esFamiliar()).isTrue();
    }

    @Test
    void debeIdentificarHermanoComoFamiliar() {
        assertThat(TipoRelacion.HERMANO.esFamiliar()).isTrue();
    }

    @Test
    void debeIdentificarHermanaComoFamiliar() {
        assertThat(TipoRelacion.HERMANA.esFamiliar()).isTrue();
    }

    @Test
    void debeIdentificarConocidoComoNoFamiliar() {
        assertThat(TipoRelacion.CONOCIDO.esFamiliar()).isFalse();
    }

    @Test
    void debeIdentificarOtroComoNoFamiliar() {
        assertThat(TipoRelacion.OTRO.esFamiliar()).isFalse();
    }

    @Test
    void debeTenerDiezValores() {
        assertThat(TipoRelacion.values()).hasSize(10);
    }
}
