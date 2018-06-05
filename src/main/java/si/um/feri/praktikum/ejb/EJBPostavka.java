package si.um.feri.praktikum.ejb;

import si.um.feri.praktikum.vao.Postavka;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@LocalBean
@Stateless
public class EJBPostavka {

    @PersistenceContext
    private EntityManager entityManager;

    public void addPostavka(Postavka p) {
        entityManager.persist(p);
    }

    public List<Postavka> vrniVsePostavke() {
        return entityManager.createQuery("SELECT p FROM Postavka p").getResultList();
    }

    public List<Postavka> vrniVsePostavkeZaDan(int idDan) {
        return entityManager.createQuery("SELECT p FROM Postavka p WHERE p.tkIdDan.idDan = " + idDan).getResultList();
    }

    public List<Postavka> vrniVsePostavkeZaVadbo(int idVadba) {
        return entityManager.createQuery("SELECT p FROM Postavka p WHERE p.tkIdVadba.idVadba = " + idVadba).getResultList();
    }

    public Postavka postavkaById(int idPostavka) {
        return entityManager.find(Postavka.class, idPostavka);
    }

    public void removePostavka(int idPostavka) {
        entityManager.remove(entityManager.find(Postavka.class, idPostavka));
    }

    public void removePostavkaZaDanInVadbo(int idDan, int idVadba) {
        Postavka p = (Postavka) entityManager.createQuery("SELECT p FROM Postavka p WHERE p.tkIdVadba.idVadba = " + idVadba + " AND p.tkIdDan.idDan = " + idDan).getSingleResult();
        entityManager.remove(p);
    }

}
