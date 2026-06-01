package co.edu.udec.guarderia.domain.events;

import java.time.Instant;
import java.time.LocalDate;

public record NinoDadoDeBajaEvent(String ninoId, LocalDate fechaBaja, Instant fecha) {
    public NinoDadoDeBajaEvent(String ninoId, LocalDate fechaBaja) {
        this(ninoId, fechaBaja, Instant.now());
    }
}
