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
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.util.Date;

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

    public void zakljuciDan(String email, int idDan) {
        Oseba oseba = ejbOseba.osebByEmail(email);
        Dan dan = ejbDan.danById(idDan);

        izvedbaDneva.setTkIdOseba(oseba);
        izvedbaDneva.setTkIdDan(dan);
        izvedbaDneva.setCasIzvedbe(new Date());

        System.out.println(izvedbaDneva.getPocutje());

        //ejbIzvedba.addIzvedba(izvedbaDneva);

        izvedbaDneva = new Izvedba();

        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("dneviPrograma.xhtml?idProgram=" + dan.getTkIdProgram().getIdProgram());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
