package co.edu.udec.tienda_musica.application.service;

import co.edu.udec.tienda_musica.application.port.input.ListarVentasPorFechaUseCase;
import co.edu.udec.tienda_musica.application.port.output.VentaRepositoryPort;
import co.edu.udec.tienda_musica.domain.entity.VentaEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
public class ListarVentasPorFechaService implements ListarVentasPorFechaUseCase {

    private final VentaRepositoryPort ventaRepository;

    public ListarVentasPorFechaService(VentaRepositoryPort ventaRepository) {
        this.ventaRepository = ventaRepository;
    }

    @Override
    public List<VentaEntity> ejecutar(LocalDate fecha) {
        LocalDateTime inicio = fecha.atStartOfDay();
        LocalDateTime fin = fecha.atTime(LocalTime.MAX);
        return ventaRepository.findByFechaVentaBetween(inicio, fin);
    }
}
