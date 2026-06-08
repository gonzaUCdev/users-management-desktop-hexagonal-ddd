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
@Table(name = "Obra")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ObraEntity {

    @Id
    @Column(name = "isbn", length = 20)
    private String isbn;

    @Column(name = "nombre", length = 100, nullable = false)
    private String nombre;

    @Column(name = "genero", length = 50)
    private String genero;
}
