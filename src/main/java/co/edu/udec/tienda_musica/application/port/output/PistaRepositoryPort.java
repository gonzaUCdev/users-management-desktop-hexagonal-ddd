package co.edu.udec.tienda_musica.application.port.output;

import co.edu.udec.tienda_musica.domain.entity.PistaEntity;
import java.util.List;

public interface PistaRepositoryPort {

    List<PistaEntity> findAll();

    List<PistaEntity> findByIsbnCd(String isbnCd);
}
