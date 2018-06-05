package si.um.feri.praktikum.ejb;

import si.um.feri.praktikum.vao.Oseba;
import si.um.feri.praktikum.vao.Program;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@LocalBean
@Stateless
public class EJBOseba {

    @PersistenceContext
    private EntityManager entityManager;
    @EJB
    private EJBProgram ejbProgram;

    public List<Oseba> vrniVseOsebe() {
        return entityManager.createQuery("SELECT o FROM Oseba o").getResultList();
    }

    public Oseba osebById(int idOseba) {
        return entityManager.find(Oseba.class, idOseba);
    }

    public void addOseba(Oseba o) {
        entityManager.persist(o);
    }

    public Oseba mergeOseba(Oseba o) {
        if(o.getIdOseba() > 0) {
            entityManager.merge(o);
            return entityManager.find(Oseba.class, o.getIdOseba());
        }

        return o;
    }

    public void deleteOseba(int idOseba) {
        Oseba oseba = osebById(idOseba);
        oseba.setTkIdProgram(null);

        List<Program> vsiProgrami = ejbProgram.vrniVsePrograme();

        for (int i=0; i<vsiProgrami.size(); i++) {
            for(int j=0; j<vsiProgrami.get(i).getTkIdOseba().size(); j++) {
                if(vsiProgrami.get(i).getTkIdOseba().get(j).getIdOseba() == idOseba) {
                    vsiProgrami.get(i).getTkIdOseba().remove(j);
                    ejbProgram.mergeProgram(vsiProgrami.get(i));
                }
            }
        }

        mergeOseba(oseba);

        entityManager.createQuery("UPDATE Meritev m SET m.tkIdOseba = null WHERE m.tkIdOseba.idOseba = " + idOseba).executeUpdate();
        entityManager.remove(entityManager.find(Oseba.class, idOseba));
    }

    public boolean validateEmail(String email) {
        return entityManager.createQuery("SELECT o FROM Oseba o WHERE o.email = '" + email + "'").getResultList().size() == 0;
    }
}
