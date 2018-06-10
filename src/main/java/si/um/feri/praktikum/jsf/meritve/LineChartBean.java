package si.um.feri.praktikum.jsf.meritve;

import lombok.Getter;
import lombok.Setter;
import org.chartistjsf.model.chart.*;
import org.primefaces.event.ItemSelectEvent;
import si.um.feri.praktikum.ejb.EJBMeritev;
import si.um.feri.praktikum.vao.Meritev;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.text.SimpleDateFormat;
import java.util.List;

@ManagedBean(name = "lineChartBean")
@ViewScoped
public class LineChartBean {
    @EJB
    private EJBMeritev ejbMeritev;

    @Getter
    @Setter
    private String emailOsebe;

    @Getter
    @Setter
    private List<Meritev> meritveOsebeList;

    private LineChartModel lineChartModel;

    @PostConstruct
    public void init(String email) {
        emailOsebe = email;
        createLineModel();
    }

    private void createLineModel() {
        meritveOsebeList = ejbMeritev.vrniMeritvePoEmail(emailOsebe);

        lineChartModel = new LineChartModel();
        LineChartSeries lineChartSeriesTeza = new LineChartSeries();
        lineChartSeriesTeza.setName("Teža");
        LineChartSeries lineChartSeriesITM = new LineChartSeries();
        lineChartSeriesITM.setName("ITM");
        LineChartSeries lineChartSeriesObsegPasu = new LineChartSeries();
        lineChartSeriesObsegPasu.setName("Obseg pasu");

        lineChartModel.setAspectRatio(AspectRatio.GOLDEN_SECTION);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM.dd.yyyy");

        for (Meritev trMeritev : meritveOsebeList) {
            lineChartModel.addLabel(simpleDateFormat.format(trMeritev.getDatumVpisa()));
        }

        for (Meritev trMeritev : meritveOsebeList) {
            lineChartSeriesTeza.set(trMeritev.getTeza());
            lineChartSeriesITM.set((trMeritev.getTeza()) / (Math.pow((trMeritev.getVisina() / 100), 2)));
            lineChartSeriesObsegPasu.set(trMeritev.getObsegPasu());
        }

        Axis xAxis = lineChartModel.getAxis(AxisType.X);
        Axis yAxis = lineChartModel.getAxis(AxisType.Y);

        lineChartModel.addSeries(lineChartSeriesTeza);
        lineChartModel.addSeries(lineChartSeriesObsegPasu);
        lineChartModel.addSeries(lineChartSeriesITM);
        lineChartModel.setAnimateAdvanced(true);
        lineChartModel.setShowTooltip(true);
    }

    public void itemSelect(ItemSelectEvent event) {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Izbrani podatek", "Vrednost: " +
                lineChartModel.getSeries().get(event.getSeriesIndex()).getData().get(event.getItemIndex())
                + ", Serija: " +
                lineChartModel.getSeries().get(event.getSeriesIndex()).getName());
        FacesContext.getCurrentInstance().addMessage(event.getComponent().getClientId(), msg);
    }

    public LineChartModel getLineModel() {
        return lineChartModel;
    }

    public void setLineModel(LineChartModel lineModel) {
        this.lineChartModel = lineModel;
    }

}



