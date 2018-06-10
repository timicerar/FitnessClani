package si.um.feri.praktikum.ejb;

import si.um.feri.praktikum.vao.Meritev;
import si.um.feri.praktikum.vao.Oseba;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@LocalBean
@Stateless
public class EJBMeritev {
    @EJB
    private EJBOseba ejbOseba;
    @PersistenceContext
    private EntityManager entityManager;

    public List<Meritev> vrniVseMeritve() {
        return entityManager.createQuery("SELECT m FROM Meritev m").getResultList();
    }

    public List<Meritev> vrniMeritvePoId(int idOseba) {
        return entityManager.createQuery("SELECT m FROM Meritev m WHERE m.tkIdOseba.idOseba = " + idOseba).getResultList();
    }

    public List<Meritev> vrniMeritvePoEmail(String email) {
        return entityManager.createQuery("SELECT m FROM Meritev m WHERE m.tkIdOseba.email = '" + email + "'").getResultList();
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

    public boolean jeOsebaDanesZeVpisalaMeritev(String email) throws ParseException {
        Oseba oseba = ejbOseba.osebByEmail(email);

        if (vrniMeritvePoId(oseba.getIdOseba()).size() > 0) {
            Meritev zadnjaMeritev = vrniZadnjoMeritev(oseba.getIdOseba());

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            Calendar calendar = Calendar.getInstance();
            Date date = calendar.getTime();
            String tempDate = sdf.format(date);

            return zadnjaMeritev.getDatumVpisa().after(df.parse(tempDate)) && zadnjaMeritev.getDatumVpisa().before(new Date());
        }

        return false;
    }

}
