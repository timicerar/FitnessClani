package si.um.feri.praktikum.ejb;

import si.um.feri.praktikum.vao.Postavka;
import si.um.feri.praktikum.vao.Vadba;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@LocalBean
@Stateless
public class EJBVadba {

    @PersistenceContext
    EntityManager entityManager;

    @EJB
    private EJBPostavka ejbPostavka;

    public List<Vadba> vrniVseVadbe() {
        return entityManager.createQuery("SELECT v FROM Vadba v").getResultList();
    }

    public Vadba vadbaById(int idVadba) {
        return entityManager.find(Vadba.class, idVadba);
    }

    public void addVadba(Vadba v) {
        entityManager.persist(v);
    }

    public Vadba mergeVadba(Vadba v) {
        if (v.getIdVadba() > 0){
            entityManager.merge(v);
            return entityManager.find(Vadba.class, v.getIdVadba());
        }

        return v;
    }

    public boolean validateNazivVadbe(String naziv) {
        return entityManager.createQuery("SELECT v FROM Vadba v WHERE v.naziv = '" + naziv + "'").getResultList().size() == 0;
    }

    public void deleteVadba(int idVadba) {
        List<Postavka> listVsehPostavk = ejbPostavka.vrniVsePostavkeZaVadbo(idVadba);
        Vadba vadba = vadbaById(idVadba);

        for (Postavka trPostavka : listVsehPostavk) {
            ejbPostavka.removePostavka(trPostavka.getIdPostavka());
        }

        entityManager.createQuery("UPDATE Znacka z SET z.tkIdVadba = null WHERE z.tkIdVadba = " + vadba.getIdVadba()).executeUpdate();

        entityManager.remove(vadba);
    }
        //SELECT p FROM Teacher t JOIN t.phones p WHERE t.firstName = :firstName
    public List<Vadba> vrniVadbeZaRoke() {
        return entityManager.createQuery("SELECT v FROM Znacka z JOIN z.tkIdVadba v WHERE z.naziv = 'roke'").getResultList();
    }

    public List<Vadba> vrniVadbeZaNoge() {
        return entityManager.createQuery("SELECT v FROM Znacka z JOIN z.tkIdVadba v WHERE z.naziv = 'noge'").getResultList();
    }

    public List<Vadba> vrniVadbeZaPrsa() {
        return entityManager.createQuery("SELECT v FROM Znacka z JOIN z.tkIdVadba v WHERE z.naziv = 'prsa'").getResultList();
    }

    public List<Vadba> vrniVadbeZaTrebusne() {
        return entityManager.createQuery("SELECT v FROM Znacka z JOIN z.tkIdVadba v WHERE z.naziv = 'trebusne'").getResultList();
    }

    public List<Vadba> vrniVadbeZaHrbet() {
        return entityManager.createQuery("SELECT v FROM Znacka z JOIN z.tkIdVadba v WHERE z.naziv = 'hrbet'").getResultList();
    }

    public List<Vadba> vrniVadbeZaRit() {
        return entityManager.createQuery("SELECT v FROM Znacka z JOIN z.tkIdVadba v WHERE z.naziv = 'rit'").getResultList();
    }
}
