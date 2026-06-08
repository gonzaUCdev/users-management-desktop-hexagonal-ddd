package co.edu.udec.tienda_musica.application.service;

import co.edu.udec.tienda_musica.application.port.input.BuscarObraPorIsbnUseCase;
import co.edu.udec.tienda_musica.application.port.output.ObraRepositoryPort;
import co.edu.udec.tienda_musica.domain.entity.ObraEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BuscarObraPorIsbnService implements BuscarObraPorIsbnUseCase {

    private final ObraRepositoryPort obraRepository;

    public BuscarObraPorIsbnService(ObraRepositoryPort obraRepository) {
        this.obraRepository = obraRepository;
    }

    @Override
    public Optional<ObraEntity> ejecutar(String isbn) {
        return obraRepository.findByIsbn(isbn);
    }
}
