package co.edu.udec.guarderia.domain.events;

import java.time.Instant;

public record AlergiaRegistradaEvent(String ninoId, String ingrediente, Instant fecha) {
    public AlergiaRegistradaEvent(String ninoId, String ingrediente) {
        this(ninoId, ingrediente, Instant.now());
    }
}
