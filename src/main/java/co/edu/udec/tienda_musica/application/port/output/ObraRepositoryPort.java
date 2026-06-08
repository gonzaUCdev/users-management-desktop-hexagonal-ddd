package co.edu.udec.tienda_musica.application.port.output;

import co.edu.udec.tienda_musica.domain.entity.ObraEntity;
import java.util.List;
import java.util.Optional;

public interface ObraRepositoryPort {

    List<ObraEntity> findAll();

    Optional<ObraEntity> findByIsbn(String isbn);

    List<ObraEntity> findByGenero(String genero);
}
