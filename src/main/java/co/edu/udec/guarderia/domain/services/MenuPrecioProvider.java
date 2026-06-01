package co.edu.udec.guarderia.domain.services;

import co.edu.udec.guarderia.domain.valueobjects.MenuId;

import java.math.BigDecimal;

@FunctionalInterface
public interface MenuPrecioProvider {
    BigDecimal getPrecio(MenuId menuId);
}
