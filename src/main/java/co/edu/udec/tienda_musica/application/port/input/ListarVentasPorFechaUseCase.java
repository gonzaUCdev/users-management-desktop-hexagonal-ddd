package co.edu.udec.tienda_musica.application.port.input;

import co.edu.udec.tienda_musica.domain.entity.VentaEntity;
import java.time.LocalDate;
import java.util.List;

public interface ListarVentasPorFechaUseCase {

    List<VentaEntity> ejecutar(LocalDate fecha);
}
