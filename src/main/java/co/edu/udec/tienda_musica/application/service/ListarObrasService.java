package co.edu.udec.tienda_musica.application.service;

import co.edu.udec.tienda_musica.application.port.input.ListarObrasUseCase;
import co.edu.udec.tienda_musica.application.port.output.ObraRepositoryPort;
import co.edu.udec.tienda_musica.domain.entity.ObraEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListarObrasService implements ListarObrasUseCase {

    private final ObraRepositoryPort obraRepository;

    public ListarObrasService(ObraRepositoryPort obraRepository) {
        this.obraRepository = obraRepository;
    }

    @Override
    public List<ObraEntity> ejecutar() {
        return obraRepository.findAll();
    }
}
