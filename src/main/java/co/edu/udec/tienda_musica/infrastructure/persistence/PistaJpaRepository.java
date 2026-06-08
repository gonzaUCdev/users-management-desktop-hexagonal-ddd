package co.edu.udec.tienda_musica.infrastructure.persistence;

import co.edu.udec.tienda_musica.application.port.output.PistaRepositoryPort;
import co.edu.udec.tienda_musica.domain.entity.PistaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PistaJpaRepository extends JpaRepository<PistaEntity, PistaEntity.PistaPk>, PistaRepositoryPort {

    List<PistaEntity> findByIsbnCd(String isbnCd);
}
