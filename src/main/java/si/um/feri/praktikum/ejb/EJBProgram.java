package si.um.feri.praktikum.ejb;

import si.um.feri.praktikum.vao.Oseba;
import si.um.feri.praktikum.vao.Program;
import si.um.feri.praktikum.vao.Vadba;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@LocalBean
@Stateless
public class EJBProgram {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Program> vrniVsePrograme() {
        return entityManager.createQuery("SELECT p FROM Program p").getResultList();
    }

    public Program programById(int idProgram) {
        return entityManager.find(Program.class, idProgram);
    }

    public void addProgram(Program p) {
        entityManager.persist(p);
    }

    @EJB
    private EJBOseba ejbOseba;
    @EJB
    private EJBVadba ejbVadba;

    public void deleteProgram(int idProgram) {
        List<Oseba> listVsehOseb = ejbOseba.vrniVseOsebe();
        List<Vadba> listVsehVadb = ejbVadba.vrniVseVadbe();

        Program program = programById(idProgram);
        program.setTkIdOseba(null);

        for (Oseba trOseba : listVsehOseb) {
            for (int i = 0; i < trOseba.getTkIdProgram().size(); i++) {
                if (trOseba.getTkIdProgram().get(i).getIdProgram() == program.getIdProgram()) {
                    trOseba.getTkIdProgram().remove(i);
                }
            }
        }

        for (Oseba trOseba : listVsehOseb) {
            ejbOseba.mergeOseba(trOseba);
        }

        for (Vadba trVadba : listVsehVadb) {
            ejbVadba.mergeVadba(trVadba);
        }

        mergeProgram(program);

        entityManager.createQuery("UPDATE Dan d SET d.tkIdProgram.idProgram = null WHERE d.tkIdProgram.idProgram = " + idProgram).executeUpdate();

        entityManager.remove(program);
    }

    public Program mergeProgram(Program p) {
        if (p.getIdProgram() > 0) {
            entityManager.merge(p);
            return entityManager.find(Program.class, p.getIdProgram());
        }

        return p;
    }

    public boolean validateNazivPrograma(String naziv) {
        return entityManager.createQuery("SELECT p FROM Program p WHERE p.naziv = '" + naziv + "'").getResultList().size() == 0;
    }
}
