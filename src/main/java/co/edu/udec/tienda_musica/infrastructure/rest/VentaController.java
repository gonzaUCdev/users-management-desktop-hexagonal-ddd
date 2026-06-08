package co.edu.udec.tienda_musica.infrastructure.rest;

import co.edu.udec.tienda_musica.application.port.input.ListarVentasPorFechaUseCase;
import co.edu.udec.tienda_musica.domain.entity.VentaEntity;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/ventas")
public class VentaController {

    private final ListarVentasPorFechaUseCase listarVentas;

    public VentaController(ListarVentasPorFechaUseCase listarVentas) {
        this.listarVentas = listarVentas;
    }

    @GetMapping
    public List<VentaEntity> listar(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha) {
        return listarVentas.ejecutar(fecha);
    }
}
