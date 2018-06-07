package si.um.feri.praktikum.jsf.dnevi;

import lombok.Getter;
import lombok.Setter;
import si.um.feri.praktikum.ejb.EJBDan;
import si.um.feri.praktikum.ejb.EJBProgram;
import si.um.feri.praktikum.vao.Dan;
import si.um.feri.praktikum.vao.Program;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.List;

@ManagedBean(name ="vrniVseDneveZaProgram")
@SessionScoped
public class VrniDneveZaProgram {
    @Getter
    @Setter
    private List<Dan> dneviZaProgram = null;
    @Getter
    @Setter
    private Program izbranProgram = new Program();
    @Getter
    private int idIzbranegaPrograma;

    @EJB
    private EJBDan ejbDan;
    @EJB
    private EJBProgram ejbProgram;

    public void setIdIzbranegaPrograma(int idIzbranegaPrograma) {
        this.idIzbranegaPrograma = idIzbranegaPrograma;
        izbranProgram = ejbProgram.programById(idIzbranegaPrograma);
        dneviZaProgram = ejbDan.vrniVseDnevePrograma(idIzbranegaPrograma);
    }
}
