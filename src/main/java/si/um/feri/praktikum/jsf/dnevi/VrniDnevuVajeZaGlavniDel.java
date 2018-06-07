package si.um.feri.praktikum.jsf.dnevi;

import lombok.Getter;
import lombok.Setter;
import si.um.feri.praktikum.ejb.EJBDan;
import si.um.feri.praktikum.vao.Postavka;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.List;

@ManagedBean(name = "vajeZaGlavniDel")
@SessionScoped
public class VrniDnevuVajeZaGlavniDel {
    @Setter
    @Getter
    private List<Postavka> vadbeZaGlavniDel = null;

    @EJB
    private EJBDan ejbDan;

    public void nastaviVadbeZaGlavniDel(int idDan) {
        vadbeZaGlavniDel = ejbDan.vrniVadbeZaDanGlavniDel(idDan);
    }

}
