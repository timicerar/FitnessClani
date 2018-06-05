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

}
