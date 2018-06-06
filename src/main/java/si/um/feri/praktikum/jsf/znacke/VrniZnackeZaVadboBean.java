package si.um.feri.praktikum.jsf.znacke;

import lombok.Getter;
import lombok.Setter;
import si.um.feri.praktikum.ejb.EJBZnacka;
import si.um.feri.praktikum.vao.Znacka;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.List;

@ManagedBean(name = "vrniZnackeZaVadboBean")
@SessionScoped
public class VrniZnackeZaVadboBean {
    @Setter
    @Getter
    private List<Znacka> znackeZaVadbo = null;

    @EJB
    private EJBZnacka ejbZnacka;

    public void vrniZnackeZaVadbo(int idVadba) {
        znackeZaVadbo = ejbZnacka.znackeZaVadbo(idVadba);
    }
}
