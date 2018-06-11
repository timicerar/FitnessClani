package si.um.feri.praktikum.email;

import javax.ejb.Local;
import javax.ejb.Stateless;
import java.time.LocalDate;

@Stateless
@Local(EJBServiceActivator.class)
public class ServiceActivatorBean implements EJBServiceActivator {

    @Override
    public void posljiMailNovemuClanu(String ime, String priimek, String email) {
        LocalDate datum = LocalDate.now();

        String besedilo = "Dobrodošli, "
                + ime
                + " "
                + priimek
                + "!"
                + "<br/><br/>Uspešno ste bili včlanjeni v Fitness!"
                + "<br/><br/>V našo aplikacijo se lahko prijavite preko Faceebook profila ali z Google računom. Uporabiti morate email, ki ste ga podali receptorju ob včlanitvi v Fitness."
                + "<br/><br/>Datum včlanitve v Fitness: " + datum
                + "<br/><br/>Lep pozdrav," + "<br/>Fitness";

        SendEmail.posljiEmail(email, "Potrdilo o uspešnem včlantvu v Fitness", besedilo);
    }

    @Override
    public void posljiMailZaPrijavoNaProgram(String ime, String priimek, String email, String program) {
        LocalDate datum = LocalDate.now();

        String besedilo = "Pozdravljeni, "
                + ime
                + " "
                + priimek
                + "!"
                + "<br/><br/>Prijava na program " + program +" je bila uspešna!"
                + "<br/><br/>Fitness vam želi dobro izvajanje vadb. Če boste potrebovali kakršnokoli pomoč se lahko oglasite v našem fitnesu."
                + "<br/><br/>Datum prijave na fitnes program: " + datum
                + "<br/><br/>Lep pozdrav," + "<br/>Fitness";

        SendEmail.posljiEmail(email, "Prijava na fitnes program", besedilo);
    }

    @Override
    public void posljiMailZaKoncanProgram(String ime, String priimek, String email, String program) {
        LocalDate datum = LocalDate.now();

        String besedilo = "Pozdravljeni, "
                + ime
                + " "
                + priimek
                + "!"
                + "<br/><br/>Čestitamo, uspešno ste končali " + program + "!"
                + "<br/><br/>Fitness vam ponuja še mnogo drugih programov, ki jih lahko pregledate na naši spletni strani!"
                + "<br/><br/>Datum zaključka fitnes programa: " + datum
                + "<br/><br/>Lep pozdrav," + "<br/>Fitness";

        SendEmail.posljiEmail(email, "Zaključen fitnes program", besedilo);
    }

}
