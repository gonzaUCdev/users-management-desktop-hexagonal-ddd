package co.edu.udec.guarderia.domain.services;

import co.edu.udec.guarderia.domain.exceptions.CostoException;
import co.edu.udec.guarderia.domain.model.Nino;
import co.edu.udec.guarderia.domain.model.RegistroConsumo;

import java.math.BigDecimal;

public class CostoMensualService {

    private final BigDecimal costoFijoMensual;
    private final MenuPrecioProvider menuPrecioProvider;

    public CostoMensualService(BigDecimal costoFijoMensual, MenuPrecioProvider menuPrecioProvider) {
        if (costoFijoMensual == null) throw new IllegalArgumentException("El costo fijo mensual no puede ser nulo");
        if (costoFijoMensual.compareTo(BigDecimal.ZERO) < 0) {
            throw CostoException.porqueCostoFijoInvalido();
        }
        if (menuPrecioProvider == null) throw new IllegalArgumentException("El proveedor de precios no puede ser nulo");

        this.costoFijoMensual = costoFijoMensual;
        this.menuPrecioProvider = menuPrecioProvider;
    }

    public BigDecimal calcularCostoMensual(Nino nino, int anio, int mes) {
        if (nino == null) throw new IllegalArgumentException("El nino no puede ser nulo");
        if (mes < 1 || mes > 12) throw CostoException.porqueMesInvalido();
        if (anio <= 0) throw CostoException.porqueAnioInvalido();

        BigDecimal costoComidas = nino.getRegistrosConsumo().stream()
                .filter(RegistroConsumo::isComio)
                .filter(r -> r.ocurrioEnMes(anio, mes))
                .map(r -> menuPrecioProvider.getPrecio(r.getMenuId()))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return costoFijoMensual.add(costoComidas);
    }

    public BigDecimal getCostoFijoMensual() {
        return costoFijoMensual;
    }
}
