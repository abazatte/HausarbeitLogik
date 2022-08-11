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
		for (double wert : werte) {
			tmp.add(wert);
		}

		return tmp;
	}
	
	public void plotVorbereiten(Command command, double vonX, double bisX, double vonY, double bisY) {
		double rangeX = bisX - vonX;
		double rangeY = bisY - vonY;
		List<Double> x = NumpyUtils.linspace(rangeX, rangeY, 256);
		List<Double> input;
		
		
		if(command instanceof Cosinus) {
			input = x.stream().map(Math::cos).collect(Collectors.toList());
		} else if(command instanceof Sinus) {
			input = x.stream().map(Math::sin).collect(Collectors.toList());
		}
	
	}

	public void testDaten() {
		List<Double> x = NumpyUtils.linspace(-Math.PI, Math.PI, 256);
		
		// List<Double> C = x.stream().map(Math::cos).collect(Collectors.toList());
		// List<Double> S = x.stream().map(Math::sin).collect(Collectors.toList());
		// List<Double> cock = x.stream().map(Math::).collect(Collectors.toList());
		
		Sinus s = new Sinus();
		List<Double> werte = new ArrayList<Double>();

		for (Double d : x) {
			// Ergebnis hier:
			/*
			String erg = (s.execute(d, d)).toString(); // Hier steht das Ergebnis von Prolog
			int start = erg.indexOf("=");
			int end = erg.indexOf("}");
			
			werte.add(Double.parseDouble(erg.substring(start + 1, end))) ;*/
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
		this.setTitle("Das ist ein Test");
		this.legendeAnzeigen();
		this.plotAnzeigen();
	}

}