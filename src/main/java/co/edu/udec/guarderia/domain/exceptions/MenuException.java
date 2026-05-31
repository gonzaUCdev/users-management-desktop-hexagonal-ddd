package co.edu.udec.guarderia.domain.exceptions;

public final class MenuException extends DomainException {

    private MenuException(String message) {
        super(message);
    }

    public static MenuException porqueIdInvalido() {
        return new MenuException("El ID del menu es invalido");
    }

    public static MenuException porquePlatoDuplicado() {
        return new MenuException("El plato ya existe en el menu");
    }

    public static MenuException porquePlatoInvalido() {
        return new MenuException("El plato no puede ser nulo");
    }

    public static MenuException porqueSinPlatos() {
        return new MenuException("El menu debe tener al menos un plato");
    }

    public static MenuException porqueNoEncontrado(Integer menuId) {
        return new MenuException("No se encontro el menu con ID " + menuId);
    }
}
