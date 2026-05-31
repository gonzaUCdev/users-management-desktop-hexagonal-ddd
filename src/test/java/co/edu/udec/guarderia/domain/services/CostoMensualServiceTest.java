package co.edu.udec.guarderia.domain.services;

import co.edu.udec.guarderia.domain.model.Nino;
import co.edu.udec.guarderia.domain.model.RegistroConsumo;
import co.edu.udec.guarderia.domain.valueobjects.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

class CostoMensualServiceTest {

    private CostoMensualService service;
    private Nino nino;
    private Map<MenuId, BigDecimal> precios;

    @BeforeEach
    void setUp() {
        precios = new HashMap<>();
        precios.put(new MenuId(1), new BigDecimal("8.00"));
        precios.put(new MenuId(2), new BigDecimal("10.00"));

        var costoFijo = new BigDecimal("200000");
        service = new CostoMensualService(costoFijo, precios::get);

        nino = new Nino(
                new NinoId("MAT001"),
                new NombreCompleto("Juan Perez"),
                new FechaNacimiento(LocalDate.of(2020, 5, 15)),
                LocalDate.of(2024, 1, 10),
                new Direccion("Calle 123")
        );
    }

    @Test
    void debeCalcularCostoMensualSinComidas() {
        var costo = service.calcularCostoMensual(nino, 2024, 3);

        assertThat(costo).isEqualByComparingTo("200000");
    }

    @Test
    void debeCalcularCostoMensualConComidas() {
        nino.agregarRegistroConsumo(new RegistroConsumo(LocalDate.of(2024, 3, 1), new MenuId(1), true));
        nino.agregarRegistroConsumo(new RegistroConsumo(LocalDate.of(2024, 3, 5), new MenuId(2), true));
        nino.agregarRegistroConsumo(new RegistroConsumo(LocalDate.of(2024, 3, 10), new MenuId(1), false));

        var costo = service.calcularCostoMensual(nino, 2024, 3);

        assertThat(costo).isEqualByComparingTo("200018");
    }

    @Test
    void soloCuentaComidasDelMesIndicado() {
        nino.agregarRegistroConsumo(new RegistroConsumo(LocalDate.of(2024, 3, 1), new MenuId(1), true));
        nino.agregarRegistroConsumo(new RegistroConsumo(LocalDate.of(2024, 4, 1), new MenuId(2), true));

        var costoMarzo = service.calcularCostoMensual(nino, 2024, 3);
        assertThat(costoMarzo).isEqualByComparingTo("200008");

        var costoAbril = service.calcularCostoMensual(nino, 2024, 4);
        assertThat(costoAbril).isEqualByComparingTo("200010");
    }

    @Test
    void debeFallarConMesInvalido() {
        assertThatThrownBy(() -> service.calcularCostoMensual(nino, 2024, 13))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("mes");
    }

    @Test
    void debeFallarConAnioInvalido() {
        assertThatThrownBy(() -> service.calcularCostoMensual(nino, 0, 1))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("anio");
    }

    @Test
    void debeFallarConNinoNulo() {
        assertThatThrownBy(() -> service.calcularCostoMensual(null, 2024, 1))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void debeFallarConCostoFijoNegativo() {
        assertThatThrownBy(() -> new CostoMensualService(new BigDecimal("-1"), precios::get))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("costo fijo");
    }

    @Test
    void debeAceptarCostoFijoCero() {
        var svc = new CostoMensualService(BigDecimal.ZERO, precios::get);
        assertThat(svc.getCostoFijoMensual()).isEqualByComparingTo(BigDecimal.ZERO);
    }

    @Test
    void debeFallarConProveedorPreciosNulo() {
        assertThatThrownBy(() -> new CostoMensualService(BigDecimal.TEN, null))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
