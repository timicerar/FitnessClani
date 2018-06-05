package si.um.feri.praktikum.ejb;

import si.um.feri.praktikum.vao.Meritev;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@LocalBean
@Stateless
public class EJBMeritev {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Meritev> vrniVseMeritve() {
        return entityManager.createQuery("SELECT m FROM Meritev m").getResultList();
    }

    public List<Meritev> vrniMeritvePoId(int idOseba) {
        return entityManager.createQuery("SELECT m FROM Meritev m WHERE m.tkIdOseba.idOseba = " + idOseba).getResultList();
    }

    public Meritev vrniZadnjoMeritev(int idOseba) {
        return (Meritev) entityManager.createQuery("SELECT m FROM Meritev m WHERE m.tkIdOseba.idOseba = " + idOseba + " AND m.datumVpisa = (SELECT MAX(m1.datumVpisa) FROM Meritev m1 WHERE m1.tkIdOseba.idOseba = " + idOseba + ")").getSingleResult();
    }

    public Meritev meritevById(int idMeritev) {
        return entityManager.find(Meritev.class, idMeritev);
    }

    public void addMeritev(Meritev m) {
        entityManager.persist(m);
    }

    public Meritev mergeMeritev(Meritev m) {
        if(m.getIdMeritev() > 0) {
            entityManager.merge(m);
            return entityManager.find(Meritev.class, m.getIdMeritev());
        }

        return m;
    }

    public void deleteMeritev(int idMeritev) {
        entityManager.remove(entityManager.find(Meritev.class, idMeritev));
    }

}
