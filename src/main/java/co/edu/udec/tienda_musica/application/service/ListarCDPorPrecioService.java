package co.edu.udec.tienda_musica.application.service;

import co.edu.udec.tienda_musica.application.port.input.ListarCDPorPrecioUseCase;
import co.edu.udec.tienda_musica.application.port.output.CDIndividualRepositoryPort;
import co.edu.udec.tienda_musica.domain.entity.CDIndividualEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ListarCDPorPrecioService implements ListarCDPorPrecioUseCase {

    private final CDIndividualRepositoryPort cdRepository;

    public ListarCDPorPrecioService(CDIndividualRepositoryPort cdRepository) {
        this.cdRepository = cdRepository;
    }

    @Override
    public List<CDIndividualEntity> ejecutar(BigDecimal minPrecio, BigDecimal maxPrecio) {
        return cdRepository.findByPvpBetween(minPrecio, maxPrecio);
    }
}
