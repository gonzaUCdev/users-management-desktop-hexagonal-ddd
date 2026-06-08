package co.edu.udec.tienda_musica.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "CD_Individual")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CDIndividualEntity {

    @Id
    @Column(name = "isbn", length = 20)
    private String isbn;

    @Column(name = "num_tienda", nullable = false)
    private Integer numTienda;

    @Column(name = "pvp", nullable = false, precision = 10, scale = 2)
    private BigDecimal pvp;

    @ManyToOne
    @JoinColumn(name = "isbn", insertable = false, updatable = false)
    private ObraEntity obra;
}
