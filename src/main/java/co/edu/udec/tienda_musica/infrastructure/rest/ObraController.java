package co.edu.udec.tienda_musica.infrastructure.rest;

import co.edu.udec.tienda_musica.application.port.input.BuscarObraPorIsbnUseCase;
import co.edu.udec.tienda_musica.application.port.input.ListarObrasUseCase;
import co.edu.udec.tienda_musica.domain.entity.ObraEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/obras")
public class ObraController {

    private final ListarObrasUseCase listarObras;
    private final BuscarObraPorIsbnUseCase buscarObra;

    public ObraController(ListarObrasUseCase listarObras, BuscarObraPorIsbnUseCase buscarObra) {
        this.listarObras = listarObras;
        this.buscarObra = buscarObra;
    }

    @GetMapping
    public List<ObraEntity> listar() {
        return listarObras.ejecutar();
    }

    @GetMapping("/{isbn}")
    public ResponseEntity<ObraEntity> buscar(@PathVariable String isbn) {
        return buscarObra.ejecutar(isbn)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
