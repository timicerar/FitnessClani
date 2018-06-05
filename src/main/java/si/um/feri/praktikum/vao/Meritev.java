package si.um.feri.praktikum.vao;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "Meritev")
@Table(name = "Meritev")
public class Meritev {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private int idMeritev;
    @Column(nullable = false, name = "Teza")
    @Getter
    @Setter
    private double teza;
    @Column(nullable = false, name = "Visina")
    @Getter
    @Setter
    private double visina;
    @Column(nullable = false, name = "ObsegPasu")
    @Getter
    @Setter
    private double obsegPasu;
    @Column(nullable = false, name = "DatumVpisa")
    @Getter
    @Setter
    private Date datumVpisa;
    @ManyToOne(targetEntity = Oseba.class, fetch = FetchType.EAGER)
    @Getter
    @Setter
    private Oseba tkIdOseba;

    public Meritev() {
    }

    public Meritev(int idMeritev, double teza, double visina, double obsegPasu, Date datumVpisa, Oseba tkIdOseba) {
        this.idMeritev = idMeritev;
        this.teza = teza;
        this.visina = visina;
        this.obsegPasu = obsegPasu;
        this.datumVpisa = datumVpisa;
        this.tkIdOseba = tkIdOseba;
    }
}
