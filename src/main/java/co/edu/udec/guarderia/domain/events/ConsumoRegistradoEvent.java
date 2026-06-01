package co.edu.udec.guarderia.domain.events;

import java.time.Instant;
import java.time.LocalDate;

public record ConsumoRegistradoEvent(String ninoId, Integer menuId, LocalDate fechaConsumo,
                                      boolean comio, Instant fecha) {
    public ConsumoRegistradoEvent(String ninoId, Integer menuId, LocalDate fechaConsumo, boolean comio) {
        this(ninoId, menuId, fechaConsumo, comio, Instant.now());
    }
}
