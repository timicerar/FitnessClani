package si.um.feri.praktikum.jsf.postavke;

import lombok.Getter;
import lombok.Setter;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import si.um.feri.praktikum.ejb.EJBPostavka;
import si.um.feri.praktikum.ejb.EJBVadba;
import si.um.feri.praktikum.vao.Postavka;
import si.um.feri.praktikum.vao.Vadba;

import javax.ejb.EJB;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import java.io.ByteArrayInputStream;

@ManagedBean(name = "podrobnostiPostavekBean")
@ApplicationScoped
public class PodrobnostiPostavkeBean {
    @Getter
    @Setter
    private Postavka izbranaPostavka = new Postavka();

    @Getter
    private int idIzbranePostavke;

    @EJB
    private EJBVadba ejbVadba;
    @EJB
    private EJBPostavka ejbPostavka;

    public void setidIzbranePostavke(int idIzbranePostavke) {
        this.idIzbranePostavke = idIzbranePostavke;
        izbranaPostavka = ejbPostavka.postavkaById(idIzbranePostavke);
    }

    public StreamedContent getSlika() {
        FacesContext context = FacesContext.getCurrentInstance();

        if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
            return new DefaultStreamedContent();
        } else {
            String idVadba = context.getExternalContext().getRequestParameterMap().get("idVadbaPostavke");
            Vadba vadba = ejbVadba.vadbaById(Integer.parseInt(idVadba));

            return new DefaultStreamedContent(new ByteArrayInputStream(vadba.getSlika()));
        }
    }
}
