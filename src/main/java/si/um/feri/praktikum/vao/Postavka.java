package si.um.feri.praktikum.vao;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "Postavka")
@Table(name = "Postavka")
public class Postavka {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private int idPostavka;
    @Column(nullable = false, name = "TipPostavke")
    @Getter
    @Setter
    private int tipPostavke;
    @Column(nullable = false, name = "ZaporednaStevilka")
    @Getter
    @Setter
    private int zaporednaSt;
    @Column(nullable = false, name = "SteviloSerij")
    @Getter
    @Setter
    private int steviloSerij;
    @Column(nullable = false, name = "SteviloPonovitev")
    @Getter
    @Setter
    private int steviloPonovitev;
    @Column(name = "Trajanje")
    @Getter
    @Setter
    private String trajanje;
    @Column(name = "Teza")
    @Getter
    @Setter
    private double teza;
    @Column(nullable = false, name = "Tezavnost")
    @Getter
    @Setter
    private int tezavnost;
    @ManyToOne(targetEntity = Dan.class, fetch = FetchType.EAGER)
    @Getter
    @Setter
    private Dan tkIdDan;
    @ManyToOne(targetEntity = Vadba.class, fetch = FetchType.EAGER)
    @Getter
    @Setter
    private Vadba tkIdVadba;

    public Postavka() {
    }

    public Postavka(int idPostavka, int tipPostavke, int zaporednaSt, int steviloSerij, int steviloPonovitev, String trajanje, double teza, int tezavnost, Dan tkIdDan, Vadba tkIdVadba) {
        this.idPostavka = idPostavka;
        this.tipPostavke = tipPostavke;
        this.zaporednaSt = zaporednaSt;
        this.steviloSerij = steviloSerij;
        this.steviloPonovitev = steviloPonovitev;
        this.trajanje = trajanje;
        this.teza = teza;
        this.tezavnost = tezavnost;
        this.tkIdDan = tkIdDan;
        this.tkIdVadba = tkIdVadba;
    }
}
