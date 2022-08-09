package Plotter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.github.sh0nk.matplotlib4j.NumpyUtils;
import com.github.sh0nk.matplotlib4j.Plot;

public class PlotController {
    private Plot plot;

    public PlotController() {
        this.plot = Plot.create();
    }

    public void setXLabel(String label) {
        this.plot.xlabel(label);
    }

    public void setYLabel(String label) {
        this.plot.ylabel(label);
    }

    public void setTitle(String title) {
        this.plot.title(title);
    }

    public void legendeAnzeigen() {
        this.plot.legend();
    }

    public void plotAnzeigen() {
        try {
            this.plot.show();
        } catch (Exception e) {
            System.out.println("Fehler beim generieren des Plots");
        }
    }

    public List<Double> werteEinlesen(double... werte) {
        List<Double> tmp = new ArrayList<>();

        // Werte die reinkommen in die liste speichern
        for(double wert : werte) {
            tmp.add(wert);
        }

        return tmp;
    }

    public void testDaten() {
         List<Double> x = NumpyUtils.linspace(-Math.PI, Math.PI, 256);
         List<Double> C = x.stream().map(Math::cos).collect(Collectors.toList());
         List<Double> S = x.stream().map(Math::sin).collect(Collectors.toList());

         // Testi Test
         this.plot.plot().add(Arrays.asList(3,5)).linestyle("-");
         this.setXLabel("X-Achse");
         this.setYLabel("Y-Achse");
         this.setTitle("Das ist ein Test");
         this.legendeAnzeigen();
         this.plotAnzeigen();
    }



}