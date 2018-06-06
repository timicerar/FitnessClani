package si.um.feri.praktikum.jsf.vadbe;

import lombok.Getter;
import lombok.Setter;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import si.um.feri.praktikum.ejb.EJBVadba;
import si.um.feri.praktikum.vao.Vadba;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import java.io.ByteArrayInputStream;

@ManagedBean(name = "podrobnostiVadbe")
@SessionScoped
public class PodrobnostiVadbeBean {

    @Getter
    @Setter
    private Vadba izbranaVadba = new Vadba();

    @Getter
    private int idIzbraneVadbe;

    @EJB
    private EJBVadba ejbVadba;

    public void setIdIzbraneVadbe(int idIzbraneVadbe) {
        this.idIzbraneVadbe = idIzbraneVadbe;
        izbranaVadba = ejbVadba.vadbaById(idIzbraneVadbe);
    }

    public StreamedContent getSlika() {
        FacesContext context = FacesContext.getCurrentInstance();

        if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
            return new DefaultStreamedContent();
        } else {
            return new DefaultStreamedContent(new ByteArrayInputStream(izbranaVadba.getSlika()));
        }
    }
}
