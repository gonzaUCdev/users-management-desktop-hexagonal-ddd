package co.edu.udec.tienda_musica.application.port.output;

import co.edu.udec.tienda_musica.domain.entity.CDIndividualEntity;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface CDIndividualRepositoryPort {

    List<CDIndividualEntity> findAll();

    Optional<CDIndividualEntity> findByIsbn(String isbn);

    List<CDIndividualEntity> findByPvpBetween(BigDecimal min, BigDecimal max);
}
