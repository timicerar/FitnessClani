package si.um.feri.praktikum.jsf.meritve;

import lombok.Getter;
import lombok.Setter;
import si.um.feri.praktikum.ejb.EJBMeritev;
import si.um.feri.praktikum.ejb.EJBOseba;
import si.um.feri.praktikum.vao.Meritev;
import si.um.feri.praktikum.vao.Oseba;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.text.ParseException;
import java.util.Date;

@ManagedBean(name = "meritevBean")
@SessionScoped
public class MeritevBean {

    @Getter
    @Setter
    private Meritev novaMeritev = new Meritev();
    @Getter
    @Setter
    private String teza;
    @Getter
    @Setter
    private String visina;
    @Getter
    @Setter
    private String obsegPasu;

    @EJB
    private EJBMeritev ejbMeritev;
    @EJB
    private EJBOseba ejbOseba;

    public void dodajMeritev(String email) {
        Oseba oseba = ejbOseba.osebaByEmail(email);
        novaMeritev.setTeza(Double.parseDouble(teza));
        novaMeritev.setVisina(Double.parseDouble(visina));
        novaMeritev.setObsegPasu(Double.parseDouble(obsegPasu));
        novaMeritev.setDatumVpisa(new Date());
        novaMeritev.setTkIdOseba(oseba);
        ejbMeritev.addMeritev(novaMeritev);
        novaMeritev = new Meritev();
        teza = "";
        visina = "";
        obsegPasu = "";
    }

    public void nastaviMeritev(String email) {
        Oseba oseba = ejbOseba.osebaByEmail(email);

        if(ejbMeritev.vrniMeritvePoId(oseba.getIdOseba()).size() > 0) {
            Meritev zadnjaMeritev = ejbMeritev.vrniZadnjoMeritev(oseba.getIdOseba());

            if (zadnjaMeritev != null) {
                teza = Double.toString(zadnjaMeritev.getTeza());
                visina = Double.toString(zadnjaMeritev.getVisina());
                obsegPasu = Double.toString(zadnjaMeritev.getObsegPasu());
            } else {
                teza = "";
                visina = "";
                obsegPasu = "";
            }
        }
    }

    public void izbrisiMeritev(int idMeritev) {
        ejbMeritev.deleteMeritev(idMeritev);
    }

    public boolean jeOsebaZeDodalaMeritev(String email) throws ParseException {
        return ejbMeritev.jeOsebaDanesZeVpisalaMeritev(email);
    }

}
