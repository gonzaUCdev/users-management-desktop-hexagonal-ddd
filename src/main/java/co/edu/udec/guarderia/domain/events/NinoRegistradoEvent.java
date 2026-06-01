package co.edu.udec.guarderia.domain.events;

import java.time.Instant;

public record NinoRegistradoEvent(String ninoId, String nombre, Instant fecha) {
    public NinoRegistradoEvent(String ninoId, String nombre) {
        this(ninoId, nombre, Instant.now());
    }
}
