package si.um.feri.praktikum.ejb;

import si.um.feri.praktikum.vao.Dan;
import si.um.feri.praktikum.vao.Postavka;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@LocalBean
@Stateless
public class EJBDan {

    @PersistenceContext
    private EntityManager entityManager;
    @EJB
    private EJBPostavka ejbPostavka;

    public List<Dan> vrniVseDneve() {
        return entityManager.createQuery("SELECT d FROM Dan d").getResultList();
    }

    public List<Dan> vrniVseDnevePrograma(int idProgram) {
        return entityManager.createQuery("SELECT d FROM Dan d WHERE d.tkIdProgram.idProgram = " + idProgram).getResultList();
    }

    public void addDan(Dan d) {
        entityManager.persist(d);
    }

    public Dan mergeDan(Dan d) {
        if (d.getIdDan() > 0) {
            entityManager.merge(d);
            return entityManager.find(Dan.class, d.getIdDan());
        }

        return d;
    }

    public Dan danById(int idDan) {
        return entityManager.find(Dan.class, idDan);
    }

    public void deleteDan(int idDan) {
        List<Postavka> postavkeZaDan = ejbPostavka.vrniVsePostavkeZaDan(idDan);

        for (Postavka trPostavka : postavkeZaDan) {
            ejbPostavka.removePostavka(trPostavka.getIdPostavka());
        }

        entityManager.remove(danById(idDan));
    }

    public boolean validateNazivDnevaVProgramu(int idProgram, String naziv) {
        return entityManager.createQuery("SELECT d FROM Dan d, Program p WHERE d.tkIdProgram.idProgram = p.idProgram AND p.idProgram = " + idProgram + " AND d.naziv = '" + naziv + "'").getResultList().size() == 0;
    }
}
