package co.edu.udec.tienda_musica.infrastructure.rest;

import co.edu.udec.tienda_musica.application.port.input.ListarCDPorPrecioUseCase;
import co.edu.udec.tienda_musica.domain.entity.CDIndividualEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/cds")
public class CDIndividualController {

    private final ListarCDPorPrecioUseCase listarCDPorPrecio;

    public CDIndividualController(ListarCDPorPrecioUseCase listarCDPorPrecio) {
        this.listarCDPorPrecio = listarCDPorPrecio;
    }

    @GetMapping
    public List<CDIndividualEntity> listar(
            @RequestParam(defaultValue = "0") BigDecimal minPrecio,
            @RequestParam(defaultValue = "999999") BigDecimal maxPrecio) {
        return listarCDPorPrecio.ejecutar(minPrecio, maxPrecio);
    }
}
