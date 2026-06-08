package co.edu.udec.tienda_musica.application.port.output;

import co.edu.udec.tienda_musica.domain.entity.VentaEntity;
import java.time.LocalDateTime;
import java.util.List;

public interface VentaRepositoryPort {

    List<VentaEntity> findAll();

    List<VentaEntity> findByFechaVentaBetween(LocalDateTime inicio, LocalDateTime fin);
}
