package si.um.feri.praktikum.jsf.vadbe;

import lombok.Setter;
import si.um.feri.praktikum.ejb.EJBVadba;
import si.um.feri.praktikum.vao.Vadba;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.ArrayList;
import java.util.List;

@ManagedBean(name = "vrniVadbeZaNoge")
@SessionScoped
public class VrniVadbeZaNogeBean {
    @Setter
    private List<Vadba> vadbeZaNoge = null;

    @EJB
    private EJBVadba ejbVadba;

    public List<Vadba> getVadbeZaNoge() {
        try {
            vadbeZaNoge = ejbVadba.vrniVadbeZaNoge();
        } catch (Exception e) {
            vadbeZaNoge = new ArrayList<>();
        }

        return vadbeZaNoge;
    }
}
