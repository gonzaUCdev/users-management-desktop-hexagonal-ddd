package co.edu.udec.guarderia.domain.factory;

import co.edu.udec.guarderia.domain.model.Nino;
import co.edu.udec.guarderia.domain.model.PersonaAutorizada;
import co.edu.udec.guarderia.domain.enums.TipoRelacion;
import co.edu.udec.guarderia.domain.valueobjects.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class NinoBuilderTest {

    @Test
    void debeConstruirNinoConBuilder() {
        var nino = new NinoBuilder()
                .conId(new NinoId("MAT001"))
                .conNombre(new NombreCompleto("Juan Perez"))
                .conFechaNacimiento(new FechaNacimiento(LocalDate.of(2020, 5, 15)))
                .conFechaIngreso(LocalDate.of(2024, 1, 10))
                .conDireccion(new Direccion("Calle 123"))
                .build();

        assertThat(nino.getId().value()).isEqualTo("MAT001");
        assertThat(nino.getNombre().value()).isEqualTo("Juan Perez");
        assertThat(nino.estaActivo()).isTrue();
    }

    @Test
    void debeConstruirConMetodoEstatico() {
        var nino = NinoBuilder.construirConDatosBasicos(
                new NinoId("MAT002"),
                new NombreCompleto("Maria Lopez"),
                new FechaNacimiento(LocalDate.of(2019, 3, 20)),
                LocalDate.of(2024, 2, 1),
                new Direccion("Av. Siempre Viva 742")
        );

        assertThat(nino.getId().value()).isEqualTo("MAT002");
        assertThat(nino.getNombre().value()).isEqualTo("Maria Lopez");
        assertThat(nino.estaActivo()).isTrue();
    }

    @Test
    void builderPermiteEncadenamiento() {
        var builder = new NinoBuilder()
                .conId(new NinoId("MAT003"))
                .conNombre(new NombreCompleto("Test"));

        assertThat(builder).isNotNull();
    }

    @Test
    void debeFallarBuildSinDatos() {
        assertThatThrownBy(() -> new NinoBuilder().build())
                .isInstanceOf(IllegalArgumentException.class);
    }
}
