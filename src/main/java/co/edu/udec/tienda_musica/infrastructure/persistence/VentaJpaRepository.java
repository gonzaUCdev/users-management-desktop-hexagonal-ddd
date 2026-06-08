package co.edu.udec.tienda_musica.infrastructure.persistence;

import co.edu.udec.tienda_musica.application.port.output.VentaRepositoryPort;
import co.edu.udec.tienda_musica.domain.entity.VentaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface VentaJpaRepository extends JpaRepository<VentaEntity, Integer>, VentaRepositoryPort {

    List<VentaEntity> findByFechaVentaBetween(LocalDateTime inicio, LocalDateTime fin);
}
