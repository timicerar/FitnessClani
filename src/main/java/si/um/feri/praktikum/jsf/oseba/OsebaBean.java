package si.um.feri.praktikum.jsf.oseba;

import si.um.feri.praktikum.ejb.EJBDan;
import si.um.feri.praktikum.ejb.EJBIzvedba;
import si.um.feri.praktikum.ejb.EJBOseba;
import si.um.feri.praktikum.ejb.EJBProgram;
import si.um.feri.praktikum.email.PosljiSporocilo;
import si.um.feri.praktikum.vao.Dan;
import si.um.feri.praktikum.vao.Izvedba;
import si.um.feri.praktikum.vao.Oseba;
import si.um.feri.praktikum.vao.Program;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.jms.JMSException;
import javax.naming.NamingException;
import java.io.IOException;
import java.util.List;

@ManagedBean(name = "osebaBean")
@SessionScoped
public class OsebaBean {

    @EJB
    private EJBOseba ejbOseba;
    @EJB
    private EJBProgram ejbProgram;
    @EJB
    private EJBDan ejbDan;
    @EJB
    private EJBIzvedba ejbIzvedba;

    public void prijavaNaProgram(String emailOsebe, int idProgram) throws JMSException, NamingException {
        Oseba oseba = ejbOseba.osebaByEmail(emailOsebe);
        Program program = ejbProgram.programById(idProgram);

        oseba.getTkIdProgram().add(program);
        program.getTkIdOseba().add(oseba);

        ejbOseba.mergeOseba(oseba);
        ejbProgram.mergeProgram(program);

        PosljiSporocilo.posljiPodatkeOsebeInProgram(oseba, 2, program.getNaziv());

        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("dneviPrograma.xhtml?idProgram=" + program.getIdProgram());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void zakljuciProgram(String emailOsebe, int idProgram) throws JMSException, NamingException {
        Oseba oseba = ejbOseba.osebaByEmail(emailOsebe);
        Program program = ejbProgram.programById(idProgram);

        List<Izvedba> izvedbeOsebe = ejbIzvedba.vrniVseIzvedbeOsebe(oseba.getIdOseba());

        for (Izvedba trIzvedbeOsebe : izvedbeOsebe) {
            trIzvedbeOsebe.setTkIdOseba(null);
            ejbIzvedba.mergeIzvedba(trIzvedbeOsebe);
        }

        oseba.setTkIdProgram(null);

        for (int i = 0; i < program.getTkIdOseba().size(); i++) {
            if (program.getTkIdOseba().get(i).getIdOseba() == oseba.getIdOseba()) {
                program.getTkIdOseba().remove(i);
            }
        }

        ejbOseba.mergeOseba(oseba);
        ejbProgram.mergeProgram(program);

        PosljiSporocilo.posljiPodatkeOsebeInProgram(oseba, 3, program.getNaziv());
    }

    public boolean aliJeOsebaPrijavljenaNaProgram(String email) {
        return ejbOseba.osebaByEmail(email).getTkIdProgram().size() > 0;
    }

    public boolean pokaziGumb(String email, int idProgram) {
        Oseba oseba = ejbOseba.osebaByEmail(email);

        for (int i = 0; i < oseba.getTkIdProgram().size(); i++) {
            if (oseba.getTkIdProgram().get(i).getIdProgram() == idProgram) {
                return true;
            }
        }

        return false;
    }

    public boolean aliJeOsebaZakljucilaVseDneve(String email, int idProgram) {
        Oseba oseba = ejbOseba.osebaByEmail(email);
        List<Izvedba> listIzvedbOsebe = ejbIzvedba.vrniVseIzvedbeOsebe(oseba.getIdOseba());
        List<Dan> listDnevovVProgramu = ejbDan.vrniVseDnevePrograma(idProgram);

        return listIzvedbOsebe.size() == listDnevovVProgramu.size();
    }

    public boolean jeDanZakljucen(String email, int idDan) {
        Oseba oseba = ejbOseba.osebaByEmail(email);
        Dan dan = ejbDan.danById(idDan);
        List<Izvedba> listIzvedbOsebe = ejbIzvedba.vrniVseIzvedbeOsebe(oseba.getIdOseba());

        for (Izvedba trIzvedba : listIzvedbOsebe) {
            if (trIzvedba.getTkIdOseba().getIdOseba() == oseba.getIdOseba() && trIzvedba.getTkIdDan().getIdDan() == dan.getIdDan()) {
                return true;
            }
        }

        return false;
    }

}
