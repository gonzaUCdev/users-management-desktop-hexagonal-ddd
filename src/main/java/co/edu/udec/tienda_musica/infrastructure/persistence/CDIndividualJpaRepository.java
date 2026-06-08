package co.edu.udec.tienda_musica.infrastructure.persistence;

import co.edu.udec.tienda_musica.application.port.output.CDIndividualRepositoryPort;
import co.edu.udec.tienda_musica.domain.entity.CDIndividualEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface CDIndividualJpaRepository extends JpaRepository<CDIndividualEntity, String>, CDIndividualRepositoryPort {

    List<CDIndividualEntity> findByPvpBetween(BigDecimal min, BigDecimal max);
}
