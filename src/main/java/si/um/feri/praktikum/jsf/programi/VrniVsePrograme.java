package si.um.feri.praktikum.jsf.programi;

import lombok.Setter;
import si.um.feri.praktikum.ejb.EJBProgram;
import si.um.feri.praktikum.vao.Program;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.ArrayList;
import java.util.List;

@ManagedBean(name = "vrniVsePrograme")
@SessionScoped
public class VrniVsePrograme {
    @Setter
    private List<Program> vsiProgrami = null;

    @EJB
    private EJBProgram ejbProgram;

    public List<Program> getVsiProgrami() {
        try {
            vsiProgrami = ejbProgram.vrniVsePrograme();
        } catch (Exception e) {
            vsiProgrami = new ArrayList<>();
        }

        return vsiProgrami;
    }
}
