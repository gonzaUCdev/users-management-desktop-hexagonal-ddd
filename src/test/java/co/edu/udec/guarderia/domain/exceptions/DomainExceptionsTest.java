package co.edu.udec.guarderia.domain.exceptions;

import co.edu.udec.guarderia.domain.valueobjects.NombreIngrediente;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class DomainExceptionsTest {

    @Test
    void ninoExceptionDebeCrearseCorrectamente() {
        var ex = NinoException.porqueNoEncontrado("MAT001");
        assertThat(ex).isInstanceOf(DomainException.class);
        assertThat(ex.getMessage()).contains("MAT001");
    }

    @Test
    void ninoExceptionPorFechaBajaInvalida() {
        var ex = NinoException.porqueFechaBajaInvalida();
        assertThat(ex.getMessage()).contains("fecha de baja");
    }

    @Test
    void ninoExceptionPorPersonaDuplicada() {
        var ex = NinoException.porquePersonaAutorizadaDuplicada("12345678A");
        assertThat(ex.getMessage()).contains("12345678A");
    }

    @Test
    void menuExceptionDebeCrearseCorrectamente() {
        var ex = MenuException.porqueNoEncontrado(5);
        assertThat(ex).isInstanceOf(DomainException.class);
        assertThat(ex.getMessage()).contains("5");
    }

    @Test
    void menuExceptionPorPlatoDuplicado() {
        var ex = MenuException.porquePlatoDuplicado();
        assertThat(ex.getMessage()).contains("plato ya existe");
    }

    @Test
    void alergiaExceptionDebeCrearseCorrectamente() {
        var ex = AlergiaException.porqueYaRegistrada(new NombreIngrediente("Mani"));
        assertThat(ex).isInstanceOf(DomainException.class);
        assertThat(ex.getMessage()).contains("Mani");
    }

    @Test
    void alergiaExceptionPorPlatoAlergeno() {
        var ex = AlergiaException.porquePlatoContieneAlergeno("Salsa de mani", "Mani");
        assertThat(ex.getMessage()).contains("Salsa de mani");
        assertThat(ex.getMessage()).contains("Mani");
    }

    @Test
    void costoExceptionDebeCrearseCorrectamente() {
        var ex = CostoException.porqueMesInvalido();
        assertThat(ex).isInstanceOf(DomainException.class);
        assertThat(ex.getMessage()).contains("mes");
    }

    @Test
    void costoExceptionPorAnio() {
        var ex = CostoException.porqueAnioInvalido();
        assertThat(ex.getMessage()).contains("anio");
    }
}
