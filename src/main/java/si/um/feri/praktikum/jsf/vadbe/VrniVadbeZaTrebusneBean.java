package si.um.feri.praktikum.jsf.vadbe;

import lombok.Setter;
import si.um.feri.praktikum.ejb.EJBVadba;
import si.um.feri.praktikum.vao.Vadba;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.ArrayList;
import java.util.List;

@ManagedBean(name = "vrniVadbeZaTrebusne")
@SessionScoped
public class VrniVadbeZaTrebusneBean {
    @Setter
    private List<Vadba> vadbeZaTrebusne = null;

    @EJB
    private EJBVadba ejbVadba;

    public List<Vadba> getVadbeZaTrebusne() {
        try {
            vadbeZaTrebusne = ejbVadba.vrniVadbeZaTrebusne();
        } catch (Exception e) {
            vadbeZaTrebusne = new ArrayList<>();
        }

        return vadbeZaTrebusne;
    }
}
