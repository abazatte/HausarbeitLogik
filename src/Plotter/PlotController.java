package Plotter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.github.sh0nk.matplotlib4j.NumpyUtils;
import com.github.sh0nk.matplotlib4j.Plot;
import com.github.sh0nk.matplotlib4j.builder.ContourBuilder;

import business.Command;
import business.Cosinus;
import business.Sinus;
import helper.Parser;


/**
 * 
 * @author Berkan Yildiz
 *
 */
public class PlotController {
	private Plot plot;

	/**
	 * Konstruktor des PlotControllers die den plot von Typ Plot instanziiert (mit der create Methode)
	 *
	 * @author Berkan Yildiz
	 */
	public PlotController() {
		this.plot = Plot.create();
	}

	/**
	 *
	 *
	 * @author Berkan Yildiz
	 * @param label beschreibt die x Achse
	 */
	public void setXLabel(String label) {
		this.plot.xlabel(label);
	}

	/**
	 *
	 *
	 * @author Berkan Yildiz
	 * @param label beschreibt die y Achse
	 */
	public void setYLabel(String label) {
		this.plot.ylabel(label);
	}

	/**
	 * @author Berkan Yildiz
	 * @param title beschreibt den titel
	 */
	public void setTitle(String title) {
		this.plot.title(title);
	}

	/**
	 * bei aufruf wird eine Legende des Graphen angezeigt
	 *
	 * @author Berkan Yildiz
	 */
	public void legendeAnzeigen() {
		this.plot.legend();
	}

	/**
	 * Delegation zum anzeigen des Plots
	 * @author Berkan Yildiz
	 */
	public void plotAnzeigen() {
		try {
			this.plot.show();
		} catch (Exception e) {
			System.out.println("Fehler beim generieren des Plots");
		}
	}


	/**
	 * Fuer reine Testzwecke zum schnellen visualiseren
	 *
	 * @author Berkan Yildiz
	 */
	public void testDaten() {
		List<Double> x = NumpyUtils.linspace(-Math.PI, Math.PI, 256);
		// List<Double> C = x.stream().map(Math::cos).collect(Collectors.toList());
		// List<Double> S = x.stream().map(Math::sin).collect(Collectors.toList());
		// Sinus s = new Sinus();
		
		List<Double> werte = new ArrayList<Double>();

		for (Double d : x) {
			/*
			String erg = (s.execute(d, d)).toString(); // Hier steht das Ergebnis von Prolog
			int start = erg.indexOf("=");
			int end = e
			werte.add(Double.parseDouble(erg.substring(start + 1, end))) ;
			*/
			Parser parser = new Parser();
			String functionString = parser.xInStringMitDoubleErsetzen(d, "cos(x)");
			werte.add(parser.parse(functionString));
		}

		// Testi Test
		this.plot.plot().add(Arrays.asList(1,8,7)).linestyle("-");
		this.setXLabel("X-Achse");
		this.setYLabel("Y-Achse");
		this.setTitle("Das ist ein Test");
		this.legendeAnzeigen();
		this.plotAnzeigen();
	}

	/**
	 * Die eingehende Funktion wird mit benutzerdefinierten X-Werten (von,bis)
	 * geplottet.
	 *
	 * @author Berkan Yildiz
	 *
	 * @param expression die Funktion die geplottet werden soll
	 * @param beginX start X-Achse
	 * @param endX ende X-Achse
	 */
	public void plotExpression(String expression,double beginX, double endX) {
		List<Double> xValuesList = NumpyUtils.linspace(beginX, endX, 256);
		List<Double> yValuesList = new ArrayList<>();
		
		for(Double d: xValuesList) {
			Parser parser = new Parser();
			String functionString = parser.xInStringMitDoubleErsetzen(d, expression);
			yValuesList.add(parser.parse(functionString));
		}
		
		//this.plot.plot().add(xValuesList).linestyle("-");
		this.plot.plot().add(xValuesList,yValuesList).linestyle("-");
		this.setXLabel("X-Achse");
		this.setYLabel("Y-Achse");
		this.setTitle(expression);
		this.legendeAnzeigen();
		this.plotAnzeigen();
	}

}