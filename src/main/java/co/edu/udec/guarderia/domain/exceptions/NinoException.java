package co.edu.udec.guarderia.domain.exceptions;

public final class NinoException extends DomainException {

    private NinoException(String message) {
        super(message);
    }

    public static NinoException porqueIdInvalido() {
        return new NinoException("El numero de matricula del nino es invalido");
    }

    public static NinoException porqueNombreInvalido() {
        return new NinoException("El nombre del nino es invalido");
    }

    public static NinoException porqueFechaIngresoInvalida() {
        return new NinoException("La fecha de ingreso no puede ser anterior a la fecha de nacimiento");
    }

    public static NinoException porqueFechaBajaInvalida() {
        return new NinoException("La fecha de baja no puede ser anterior a la fecha de ingreso");
    }

    public static NinoException porqueYaDadoDeBaja(String fechaBaja) {
        return new NinoException("El nino ya fue dado de baja el " + fechaBaja);
    }

    public static NinoException porqueNoEncontrado(String matricula) {
        return new NinoException("No se encontro al nino con matricula " + matricula);
    }

    public static NinoException porquePersonaAutorizadaDuplicada(String dni) {
        return new NinoException("Ya existe una persona autorizada con DNI " + dni);
    }

    public static NinoException porqueConsumoDuplicado() {
        return new NinoException("Ya existe un registro de consumo para este menu en esta fecha");
    }

    public static NinoException porqueResponsablePagoInvalido() {
        return new NinoException("El responsable de pago no puede ser nulo");
    }
}
