package si.um.feri.praktikum.jsf.vadbe;

import lombok.Setter;
import si.um.feri.praktikum.ejb.EJBVadba;
import si.um.feri.praktikum.vao.Vadba;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.ArrayList;
import java.util.List;

@ManagedBean(name = "vrniVadbeZaRit")
@SessionScoped
public class VrniVadbeZaRitBean {
    @Setter
    private List<Vadba> vadbeZaRit = null;

    @EJB
    private EJBVadba ejbVadba;

    public List<Vadba> getVadbeZaRit() {
        try {
            vadbeZaRit = ejbVadba.vrniVadbeZaRit();
        } catch (Exception e) {
            vadbeZaRit = new ArrayList<>();
        }

        return vadbeZaRit;
    }
}
