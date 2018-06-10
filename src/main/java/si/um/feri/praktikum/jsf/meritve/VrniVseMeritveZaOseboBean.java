package si.um.feri.praktikum.jsf.meritve;

import lombok.Getter;
import lombok.Setter;
import si.um.feri.praktikum.ejb.EJBMeritev;
import si.um.feri.praktikum.vao.Meritev;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.List;

@ManagedBean(name = "vrniVseMeritveZaOsebo")
@SessionScoped
public class VrniVseMeritveZaOseboBean {
    @Setter
    @Getter
    private List<Meritev> meritveOsebe = null;

    @EJB
    private EJBMeritev ejbMeritev;

    public void nastaviMeritveOsebe(String email) {
        meritveOsebe = ejbMeritev.vrniMeritvePoEmail(email);
    }
}
