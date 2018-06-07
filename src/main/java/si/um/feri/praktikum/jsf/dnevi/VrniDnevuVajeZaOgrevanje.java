package si.um.feri.praktikum.jsf.dnevi;

import lombok.Getter;
import lombok.Setter;
import si.um.feri.praktikum.ejb.EJBDan;
import si.um.feri.praktikum.vao.Postavka;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.List;

@ManagedBean(name = "vajeZaOgrevanje")
@SessionScoped
public class VrniDnevuVajeZaOgrevanje {
    @Setter
    @Getter
    private List<Postavka> vadbeZaOgrevanje = null;

    @EJB
    private EJBDan ejbDan;

    public void nastaviVadbeZaOgrevanje(int idDan) {
        vadbeZaOgrevanje = ejbDan.vrniVadbeZaDanOgrevanje(idDan);
    }

}
