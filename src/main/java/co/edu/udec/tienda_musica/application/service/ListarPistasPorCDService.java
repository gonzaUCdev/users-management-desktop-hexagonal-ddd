package co.edu.udec.tienda_musica.application.service;

import co.edu.udec.tienda_musica.application.port.input.ListarPistasPorCDUseCase;
import co.edu.udec.tienda_musica.application.port.output.PistaRepositoryPort;
import co.edu.udec.tienda_musica.domain.entity.PistaEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListarPistasPorCDService implements ListarPistasPorCDUseCase {

    private final PistaRepositoryPort pistaRepository;

    public ListarPistasPorCDService(PistaRepositoryPort pistaRepository) {
        this.pistaRepository = pistaRepository;
    }

    @Override
    public List<PistaEntity> ejecutar(String isbnCd) {
        return pistaRepository.findByIsbnCd(isbnCd);
    }
}
