package co.edu.udec.tienda_musica.application.port.input;

import co.edu.udec.tienda_musica.domain.entity.ObraEntity;
import java.util.List;

public interface ListarObrasUseCase {

    List<ObraEntity> ejecutar();
}
