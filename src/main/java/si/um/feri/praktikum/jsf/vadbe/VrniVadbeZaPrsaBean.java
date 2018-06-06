package si.um.feri.praktikum.jsf.vadbe;

import lombok.Setter;
import si.um.feri.praktikum.ejb.EJBVadba;
import si.um.feri.praktikum.vao.Vadba;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.ArrayList;
import java.util.List;

@ManagedBean(name = "vrniVadbeZaPrsa")
@SessionScoped
public class VrniVadbeZaPrsaBean {
    @Setter
    private List<Vadba> vadbeZaPrsa = null;

    @EJB
    private EJBVadba ejbVadba;

    public List<Vadba> getVadbeZaPrsa() {
        try {
            vadbeZaPrsa = ejbVadba.vrniVadbeZaPrsa();
        } catch (Exception e) {
            vadbeZaPrsa = new ArrayList<>();
        }

        return vadbeZaPrsa;
    }
}
