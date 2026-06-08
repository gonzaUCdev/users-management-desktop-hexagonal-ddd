package co.edu.udec.tienda_musica.application.port.input;

import co.edu.udec.tienda_musica.domain.entity.CDIndividualEntity;
import java.math.BigDecimal;
import java.util.List;

public interface ListarCDPorPrecioUseCase {

    List<CDIndividualEntity> ejecutar(BigDecimal minPrecio, BigDecimal maxPrecio);
}
