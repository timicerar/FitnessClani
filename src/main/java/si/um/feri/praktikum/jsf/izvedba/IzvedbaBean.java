package si.um.feri.praktikum.jsf.izvedba;

import lombok.Getter;
import lombok.Setter;
import si.um.feri.praktikum.ejb.EJBDan;
import si.um.feri.praktikum.ejb.EJBIzvedba;
import si.um.feri.praktikum.ejb.EJBOseba;
import si.um.feri.praktikum.vao.Dan;
import si.um.feri.praktikum.vao.Izvedba;
import si.um.feri.praktikum.vao.Oseba;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@ManagedBean
@SessionScoped
public class IzvedbaBean {

    @EJB
    private EJBOseba ejbOseba;
    @EJB
    private EJBDan ejbDan;
    @EJB
    private EJBIzvedba ejbIzvedba;

    @Getter
    @Setter
    private Izvedba izvedbaDneva = new Izvedba();

    @Getter
    @Setter
    private Dan izbranDan = new Dan();

    @Getter
    private int idIzbranegaDneva;

    public void zakljuciDan(String email, int idDan) throws IOException {
        Oseba oseba = ejbOseba.osebaByEmail(email);
        Dan dan = ejbDan.danById(idDan);

        izvedbaDneva.setTkIdOseba(oseba);
        izvedbaDneva.setTkIdDan(dan);
        izvedbaDneva.setCasIzvedbe(new Date());

        if (!jeOsebaZeIzvedlaTaDan(email))
            ejbIzvedba.addIzvedba(izvedbaDneva);

        izvedbaDneva = new Izvedba();

        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("dneviPrograma.xhtml?idProgram=" + dan.getTkIdProgram().getIdProgram());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean jeDanVProjektuOsebe(String email) {
        Oseba oseba = ejbOseba.osebaByEmail(email);

        for (int i = 0; i < oseba.getTkIdProgram().size(); i++) {
            if (oseba.getTkIdProgram().get(i).getIdProgram() == izbranDan.getTkIdProgram().getIdProgram()) {
                return true;
            }
        }

        return false;
    }

    public boolean jeOsebaZeIzvedlaTaDan(String email) {
        Oseba oseba = ejbOseba.osebaByEmail(email);
        List<Izvedba> listIzvedbOsebe = ejbIzvedba.vrniVseIzvedbeOsebe(oseba.getIdOseba());

        for (Izvedba trIzvedba : listIzvedbOsebe) {
            if (trIzvedba.getTkIdDan().getIdDan() == izbranDan.getIdDan() && trIzvedba.getTkIdOseba().getIdOseba() == oseba.getIdOseba()) {
                return true;
            }
        }

        return false;
    }

    public void setIdIzbranegaDneva(int idIzbranegaDneva) {
        this.idIzbranegaDneva = idIzbranegaDneva;
        izbranDan = ejbDan.danById(idIzbranegaDneva);
    }

}
