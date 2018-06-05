package si.um.feri.praktikum.vao;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "Znacka")
@Table(name = "Znacka")
public class Znacka {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private int idZnacka;
    @Column(nullable = false, name = "Naziv")
    @Getter
    @Setter
    private String naziv;
    @ManyToOne(targetEntity = Vadba.class, fetch = FetchType.EAGER)
    @Getter
    @Setter
    private Vadba tkIdVadba;

    public Znacka() {
    }

    public Znacka(String naziv, Vadba tkIdVadba) {
        this.naziv = naziv;
        this.tkIdVadba = tkIdVadba;
    }
}
