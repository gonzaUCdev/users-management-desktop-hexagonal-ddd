package co.edu.udec.guarderia.domain.enums;

public enum TipoRelacion {
    MADRE,
    PADRE,
    ABUELA,
    ABUELO,
    TIA,
    TIO,
    HERMANA,
    HERMANO,
    CONOCIDO,
    OTRO;

    public boolean esFamiliar() {
        return this != CONOCIDO && this != OTRO;
    }
}
