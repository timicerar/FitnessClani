package si.um.feri.praktikum.vao;

import lombok.Getter;
import lombok.Setter;
import si.um.feri.praktikum.jsf.programi.KopiranjePrograma;

import javax.persistence.*;
import java.util.List;

@Entity(name = "Program")
@Table(name = "Program")
public class Program implements KopiranjePrograma {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private int idProgram;
    @Column(nullable = false, name = "Naziv")
    @Getter
    @Setter
    private String naziv;
    @Column(nullable = false, name = "Opis", length = 10000)
    @Getter
    @Setter
    private String opis;
    @Column(nullable = false, name = "Intenzivnost", length = 10000)
    @Getter
    @Setter
    private int intenzivnost;
    @ManyToMany(targetEntity = Oseba.class, fetch = FetchType.EAGER)
    @Getter
    @Setter
    private List<Oseba> tkIdOseba;

    public Program() {

    }

    public Program(String naziv, String opis, int intenzivnost, List<Oseba> tkIdOseba) {
        this.naziv = naziv;
        this.opis = opis;
        this.intenzivnost = intenzivnost;
        this.tkIdOseba = tkIdOseba;
    }

    @Override
    public Program cloneProgram() {
        Program kopiranProgram = new Program(naziv + " (copy)", opis, intenzivnost, null);
        return kopiranProgram;
    }
}
