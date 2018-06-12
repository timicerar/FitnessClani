package si.um.feri.praktikum.pdf;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;
import si.um.feri.praktikum.ejb.EJBDan;
import si.um.feri.praktikum.ejb.EJBPostavka;
import si.um.feri.praktikum.vao.Dan;
import si.um.feri.praktikum.vao.Postavka;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@ManagedBean(name = "pdfBean")
@SessionScoped
public class PdfBean {

    private static SimpleDateFormat sdf1 = new SimpleDateFormat("dd_MM_yyyy_HH:mm:ss");
    private static SimpleDateFormat sdf2 = new SimpleDateFormat("dd. MM. yyyy HH:mm:ss");
    private static Font glavniNaslov = new Font(Font.FontFamily.HELVETICA, 24, Font.BOLD, BaseColor.RED);
    private static Font kategorija = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD, BaseColor.BLACK);
    private static Font tekstFont = new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL, BaseColor.BLACK);

    @EJB
    private EJBDan ejbDan;
    @EJB
    private EJBPostavka ejbPostavka;

    public void download(int idDan) throws IOException, DocumentException {
        Dan dan = ejbDan.danById(idDan);
        List<Postavka> listPostavk = ejbPostavka.vrniVsePostavkeZaDan(dan.getIdDan());

        Document document = new Document();
        String imeDatoteke = "dan" + sdf1.format(new Date()) + ".pdf";

        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + imeDatoteke + "\"");
        context.responseComplete();
        PdfWriter.getInstance(document, response.getOutputStream());

        document.addAuthor("Fitness");
        document.addCreationDate();
        document.addTitle("Trening za izbran dan");

        document.open();

        LineSeparator lineSeparator = new LineSeparator();
        lineSeparator.setOffset(-3);

        Paragraph glavniNaslov = new Paragraph(dan.getTkIdProgram().getNaziv() + " - " + dan.getNaziv());
        glavniNaslov.setFont(PdfBean.glavniNaslov);

        document.add(glavniNaslov);

        Paragraph kategorijaOgrevanje = new Paragraph("OGREVANJE");
        kategorijaOgrevanje.setFont(PdfBean.kategorija);
        kategorijaOgrevanje.add(lineSeparator);

        document.add(kategorijaOgrevanje);

        int zapSt = 1;

        for (Postavka trPostavka : listPostavk) {
            if (trPostavka.getTipPostavke() == 0) {
                dodajVadboInPostavkoNaPDF(zapSt, document, trPostavka);
                zapSt++;
            }
        }

        Paragraph kategorijaGlavniDel = new Paragraph("GLAVNI DEL TRENINGA");
        kategorijaGlavniDel.setFont(PdfBean.kategorija);
        kategorijaGlavniDel.add(lineSeparator);

        document.add(kategorijaGlavniDel);

        zapSt = 1;

        for (Postavka trPostavka : listPostavk) {
            if (trPostavka.getTipPostavke() == 1) {
                dodajVadboInPostavkoNaPDF(zapSt, document, trPostavka);
                zapSt++;
            }
        }

        Paragraph kategorijaOhlajanje = new Paragraph("OHLAJANJE");
        kategorijaOhlajanje.setFont(PdfBean.kategorija);
        kategorijaOhlajanje.add(lineSeparator);

        document.add(kategorijaOhlajanje);

        zapSt = 1;

        for (Postavka trPostavka : listPostavk) {
            if (trPostavka.getTipPostavke() == 2) {
                dodajVadboInPostavkoNaPDF(zapSt, document, trPostavka);
                zapSt++;
            }
        }

        document.close();
    }

    private void dodajVadboInPostavkoNaPDF(int i, Document document, Postavka trPostavka) throws DocumentException {
        document.add(new Paragraph(i +". Vadba - " + trPostavka.getTkIdVadba().getNaziv() + ":"));
        document.add(new Paragraph("    Opis: " + trPostavka.getTkIdVadba().getOpis()));

        if (trPostavka.getTeza() > 0)
            document.add(new Paragraph("        - Teža: " + trPostavka.getTeza() + " kg"));
        else
            document.add(new Paragraph("        - Teža: Lastna teža"));

        document.add(new Paragraph("        - Cas: " + trPostavka.getTrajanje() + " min"));
        document.add(new Paragraph("        - Serije: " + trPostavka.getSteviloSerij() + "x" + trPostavka.getSteviloPonovitev()));
        document.add(new Paragraph("        - Težavnost: 1 - (" + trPostavka.getTezavnost() + ") - 10"));
        document.add(Chunk.NEWLINE);
    }

}
