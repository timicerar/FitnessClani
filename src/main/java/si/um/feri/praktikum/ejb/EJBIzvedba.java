package si.um.feri.praktikum.ejb;

import si.um.feri.praktikum.vao.Izvedba;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@LocalBean
@Stateless
public class EJBIzvedba {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Izvedba> vrniVseIzvedbeOsebe(int idOseba) {
        return entityManager.createQuery("SELECT i FROM Izvedba i WHERE i.tkIdOseba.idOseba = " + idOseba).getResultList();
    }

    public Izvedba mergeIzvedba(Izvedba i) {
        if (i.getIdIzvedba() > 0) {
            entityManager.merge(i);
            return entityManager.find(Izvedba.class, i.getIdIzvedba());
        }

        return i;
    }

    public void addIzvedba(Izvedba i) {
        entityManager.persist(i);
    }
}
