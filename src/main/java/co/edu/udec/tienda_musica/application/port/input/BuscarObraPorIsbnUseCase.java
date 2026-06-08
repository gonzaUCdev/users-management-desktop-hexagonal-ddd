package co.edu.udec.tienda_musica.application.port.input;

import co.edu.udec.tienda_musica.domain.entity.ObraEntity;
import java.util.Optional;

public interface BuscarObraPorIsbnUseCase {

    Optional<ObraEntity> ejecutar(String isbn);
}
