package si.um.feri.praktikum.ejb;

import si.um.feri.praktikum.vao.Znacka;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@LocalBean
@Stateless
public class EJBZnacka {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Znacka> vrniVseZnacke() {
        return entityManager.createQuery("SELECT z FROM Znacka z").getResultList();
    }

    public List<Znacka> vrniVseZnackeDistinct() {
        return entityManager.createQuery("SELECT DISTINCT z.naziv FROM Znacka z").getResultList();
    }

    public List<Znacka> znackeZaVadbo(int idVadba) {
        return entityManager.createQuery("SELECT z FROM Znacka z WHERE z.tkIdVadba.idVadba = " + idVadba).getResultList();
    }

    public Znacka znackaById(int idZnacka) {
        return entityManager.find(Znacka.class, idZnacka);
    }

    public void addZnacka(Znacka v) {
        entityManager.persist(v);
    }

    public Znacka mergeZnacka(Znacka z) {
        if (z.getIdZnacka() > 0){
            entityManager.merge(z);
            return entityManager.find(Znacka.class, z.getIdZnacka());
        }

        return z;
    }

    public void removeZnacka(int idZnacka) {
        entityManager.remove(entityManager.find(Znacka.class, idZnacka));
    }
}
