package co.edu.udec.tienda_musica.infrastructure.rest;

import co.edu.udec.tienda_musica.application.port.input.ListarPistasPorCDUseCase;
import co.edu.udec.tienda_musica.domain.entity.PistaEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/pistas")
public class PistaController {

    private final ListarPistasPorCDUseCase listarPistas;

    public PistaController(ListarPistasPorCDUseCase listarPistas) {
        this.listarPistas = listarPistas;
    }

    @GetMapping
    public List<PistaEntity> listar(@RequestParam String isbnCd) {
        return listarPistas.ejecutar(isbnCd);
    }
}
