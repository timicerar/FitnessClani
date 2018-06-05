package si.um.feri.praktikum.vao;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "Izvedba")
@Table(name = "Izvedba")
public class Izvedba {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private int idIzvedba;
    @Column(nullable = false, name = "Pocutje")
    @Getter
    @Setter
    private int pocutje;
    @Column(nullable = false, name = "CasIzvedbe")
    @Getter
    @Setter
    private Date casIzvedbe;
    @ManyToOne(targetEntity = Dan.class, fetch = FetchType.EAGER)
    @Getter
    @Setter
    private Dan tkIdDan;
    @ManyToOne(targetEntity = Oseba.class, fetch = FetchType.EAGER)
    @Getter
    @Setter
    private Oseba tkIdOseba;

    public Izvedba() {
    }

    public Izvedba(int idIzvedba, int pocutje, Date casIzvedbe, Dan tkIdDan, Oseba tkIdOseba) {
        this.idIzvedba = idIzvedba;
        this.pocutje = pocutje;
        this.casIzvedbe = casIzvedbe;
        this.tkIdDan = tkIdDan;
        this.tkIdOseba = tkIdOseba;
    }
}
