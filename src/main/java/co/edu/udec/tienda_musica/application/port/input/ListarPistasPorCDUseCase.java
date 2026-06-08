package co.edu.udec.tienda_musica.application.port.input;

import co.edu.udec.tienda_musica.domain.entity.PistaEntity;
import java.util.List;

public interface ListarPistasPorCDUseCase {

    List<PistaEntity> ejecutar(String isbnCd);
}
