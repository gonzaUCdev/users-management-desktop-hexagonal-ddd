package co.edu.udec.tienda_musica.infrastructure.persistence;

import co.edu.udec.tienda_musica.application.port.output.ObraRepositoryPort;
import co.edu.udec.tienda_musica.domain.entity.ObraEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ObraJpaRepository extends JpaRepository<ObraEntity, String>, ObraRepositoryPort {

    List<ObraEntity> findByGenero(String genero);
}
