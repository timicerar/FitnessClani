package si.um.feri.praktikum.jsf.vadbe;

import lombok.Setter;
import si.um.feri.praktikum.ejb.EJBVadba;
import si.um.feri.praktikum.vao.Vadba;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.ArrayList;
import java.util.List;

@ManagedBean(name = "vrniVadbeZaHrbet")
@SessionScoped
public class VrniVadbeZaHrbetBean {
    @Setter
    private List<Vadba> vadbeZaHrbet = null;

    @EJB
    private EJBVadba ejbVadba;

    public List<Vadba> getVadbeZaHrbet() {
        try {
            vadbeZaHrbet = ejbVadba.vrniVadbeZaHrbet();
        } catch (Exception e) {
            vadbeZaHrbet = new ArrayList<>();
        }

        return vadbeZaHrbet;
    }
}
