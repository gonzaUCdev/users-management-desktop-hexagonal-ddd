package co.edu.udec.tienda_musica.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Artista")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ArtistaEntity {

    @Id
    @Column(name = "nombre_artista", length = 100)
    private String nombreArtista;

    @Column(name = "pais_origen", length = 50)
    private String paisOrigen;

    @Column(name = "estilo_musical", length = 50)
    private String estiloMusical;

    @Column(name = "tipo", length = 20)
    private String tipo;

    @Column(name = "num_integrantes")
    private Integer numIntegrantes;
}
