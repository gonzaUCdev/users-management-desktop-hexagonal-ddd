package co.edu.udec.guarderia.domain.factory;

import co.edu.udec.guarderia.domain.exceptions.MenuException;
import co.edu.udec.guarderia.domain.model.Menu;
import co.edu.udec.guarderia.domain.model.Plato;
import co.edu.udec.guarderia.domain.valueobjects.MenuId;

import java.util.List;

public class MenuFactory {

    private MenuFactory() {
    }

    public static Menu crearMenu(Integer id, List<Plato> platos) {
        if (id == null || id <= 0) {
            throw MenuException.porqueIdInvalido();
        }
        if (platos == null || platos.isEmpty()) {
            throw MenuException.porqueSinPlatos();
        }
        return new Menu(new MenuId(id), platos);
    }

    public static Menu crearMenu(MenuId menuId, List<Plato> platos) {
        if (platos == null || platos.isEmpty()) {
            throw MenuException.porqueSinPlatos();
        }
        return new Menu(menuId, platos);
    }
}
