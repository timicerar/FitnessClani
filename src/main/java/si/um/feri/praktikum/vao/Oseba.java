package si.um.feri.praktikum.vao;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity(name = "Oseba")
@Table(name = "Oseba")
public class Oseba {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private int idOseba;
    @Column(nullable = false, name = "Ime")
    @Getter
    @Setter
    private String ime;
    @Column(nullable = false, name = "Priimek")
    @Getter
    @Setter
    private String priimek;
    @Column(nullable = false, name = "Email")
    @Getter
    @Setter
    private String email;
    @Column(nullable = false, name = "Spol")
    @Getter
    @Setter
    private int spol;
    @Column(nullable = false, name = "DatumRojstva")
    @Temporal(TemporalType.DATE)
    @Getter
    @Setter
    private Date datumRojstva;
    @Column(nullable = false, name = "Telefon")
    @Getter
    @Setter
    private String telefon;
    @Column(nullable = false, name = "DatumVpisa")
    @Getter
    @Setter
    private Date datumVpisa;
    @ManyToMany(targetEntity = Program.class, fetch = FetchType.EAGER, mappedBy = "tkIdOseba")
    @Getter
    @Setter
    private List<Program> tkIdProgram;

    public Oseba() {

    }

    public Oseba(int idOseba, String ime, String priimek, String email, int spol, Date datumRojstva, String telefon, Date datumVpisa) {
        this.idOseba = idOseba;
        this.ime = ime;
        this.priimek = priimek;
        this.email = email;
        this.spol = spol;
        this.datumRojstva = datumRojstva;
        this.telefon = telefon;
        this.datumVpisa = datumVpisa;
    }
}
