package co.edu.udec.tienda_musica.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name = "Pista")
@IdClass(PistaEntity.PistaPk.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PistaEntity {

    @Id
    @Column(name = "isbn_cd", length = 20)
    private String isbnCd;

    @Id
    @Column(name = "num_pista")
    private Integer numPista;

    @Column(name = "titulo", length = 200)
    private String titulo;

    @Column(name = "duracion")
    private Integer duracion;

    @ManyToOne
    @JoinColumn(name = "isbn_cd", insertable = false, updatable = false)
    private CDIndividualEntity cd;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PistaPk implements Serializable {
        private String isbnCd;
        private Integer numPista;
    }
}
