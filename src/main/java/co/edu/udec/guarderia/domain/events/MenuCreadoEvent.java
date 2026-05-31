package co.edu.udec.guarderia.domain.events;

import java.time.Instant;

public record MenuCreadoEvent(Integer menuId, Instant fecha) {
    public MenuCreadoEvent(Integer menuId) {
        this(menuId, Instant.now());
    }
}
