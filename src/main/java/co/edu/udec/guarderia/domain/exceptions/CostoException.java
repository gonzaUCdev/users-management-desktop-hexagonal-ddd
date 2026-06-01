package co.edu.udec.guarderia.domain.exceptions;

public final class CostoException extends DomainException {

    private CostoException(String message) {
        super(message);
    }

    public static CostoException porqueCostoFijoInvalido() {
        return new CostoException("El costo fijo mensual no puede ser negativo");
    }

    public static CostoException porqueMesInvalido() {
        return new CostoException("El mes debe estar entre 1 y 12");
    }

    public static CostoException porqueAnioInvalido() {
        return new CostoException("El anio no puede ser negativo o cero");
    }

    public static CostoException porqueMenuNoEncontrado(Integer menuId) {
        return new CostoException("No se encontro el menu con ID " + menuId + " para calcular el costo");
    }
}
