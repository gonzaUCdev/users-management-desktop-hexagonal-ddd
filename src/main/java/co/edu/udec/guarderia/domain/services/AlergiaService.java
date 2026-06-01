package co.edu.udec.guarderia.domain.services;

import co.edu.udec.guarderia.domain.exceptions.AlergiaException;
import co.edu.udec.guarderia.domain.model.Menu;
import co.edu.udec.guarderia.domain.model.Nino;
import co.edu.udec.guarderia.domain.model.Plato;

import java.util.List;
import java.util.stream.Collectors;

public class AlergiaService {

    public List<Plato> obtenerPlatosNoAptos(Nino nino, Menu menu) {
        if (nino == null) throw new IllegalArgumentException("El nino no puede ser nulo");
        if (menu == null) throw new IllegalArgumentException("El menu no puede ser nulo");

        return menu.getPlatos().stream()
                .filter(plato -> !nino.puedeComerPlato(plato))
                .collect(Collectors.toUnmodifiableList());
    }

    public boolean puedeComerMenu(Nino nino, Menu menu) {
        return obtenerPlatosNoAptos(nino, menu).isEmpty();
    }

    public void verificarAlergia(Nino nino, Plato plato) {
        if (nino == null) throw new IllegalArgumentException("El nino no puede ser nulo");
        if (plato == null) throw new IllegalArgumentException("El plato no puede ser nulo");

        if (!nino.puedeComerPlato(plato)) {
            plato.getIngredientes().stream()
                    .filter(nino::tieneAlergiaA)
                    .findFirst()
                    .ifPresent(ingrediente -> {
                        throw AlergiaException.porquePlatoContieneAlergeno(
                                plato.getNombre().value(),
                                ingrediente.value());
                    });
        }
    }
}
