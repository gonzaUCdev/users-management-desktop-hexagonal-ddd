package co.edu.udec.guarderia.domain.exceptions;

import co.edu.udec.guarderia.domain.valueobjects.NombreIngrediente;

public final class AlergiaException extends DomainException {

    private AlergiaException(String message) {
        super(message);
    }

    public static AlergiaException porqueYaRegistrada(NombreIngrediente ingrediente) {
        return new AlergiaException("La alergia al ingrediente '" + ingrediente.value() + "' ya esta registrada");
    }

    public static AlergiaException porqueIngredienteInvalido() {
        return new AlergiaException("El ingrediente de la alergia no puede ser nulo");
    }

    public static AlergiaException porquePlatoContieneAlergeno(String nombrePlato, String ingrediente) {
        return new AlergiaException("El nino no puede comer el plato '" + nombrePlato
                + "' porque es alergico a '" + ingrediente + "'");
    }
}
