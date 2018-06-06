package si.um.feri.praktikum.jsf.vadbe;

import lombok.Setter;
import si.um.feri.praktikum.ejb.EJBVadba;
import si.um.feri.praktikum.vao.Vadba;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.ArrayList;
import java.util.List;

@ManagedBean(name = "vrniVadbeZaRoke")
@SessionScoped
public class VrniVadbeZaRokeBean {
    @Setter
    private List<Vadba> vadbeZaRoke = null;

    @EJB
    private EJBVadba ejbVadba;

    public List<Vadba> getVadbeZaRoke() {
        try {
            vadbeZaRoke = ejbVadba.vrniVadbeZaRoke();
        } catch (Exception e) {
            vadbeZaRoke = new ArrayList<>();
        }

        return vadbeZaRoke;
    }
}
