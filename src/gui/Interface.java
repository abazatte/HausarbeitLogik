package gui;

import business.*;
import helper.Parser;

import org.jpl7.PrologException;

//import com.github.sh0nk.matplotlib4j.NumpyUtils;

import Exception.MinMaxException;
import Plotter.PlotController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class Interface extends JFrame implements ActionListener {

	private static double MIN = -100;
	private static double MAX = 100;


	private JTextField fieldRangeVonX, fieldRangeBisX, fieldRangeVonY, fieldRangeBisY;
	private JLabel labelRangeVonX, labelRangeBisX, labelRangeVonY, labelRangeBisY;

	private List<JButton> jButtonList;

	private JButton exponent, matrixButton;
	private JButton numbers[];
	private JTextField res;
	private JPanel panel;

	private Command pl, mi, mo, di, po, mu, sq, f, cos, sin;
	private Command last;

	private double entry1;
	private double entry2;
	private double ergebnis;

	private double rangeVonX;
	private double rangeBisX;
	// private double rangeVonY; Haram wird errechnet
	// private double rangeBisY; Haram wird errechnet

	private PlotController plotController;
	private List<Double> x; // Hilfsliste
	private boolean xPressed; // x = unbekannte. z.B. e^x, sin(x), cos(x), tan(x) usw...

	/**
	 * Author: Berkan Yildiz
	 */
	public Interface() {
		super("Prolog Calculator");
		this.jButtonList = new ArrayList<>();
		this.numbers = new JButton[13]; // Pro neuen JButton einen hochzaehlen
		this.res = new JTextField(13); // Pro neuen JButton einen hochzaehlen
		this.panel = new JPanel();
		this.initButtons();
		this.initTextFelder();

		this.setLayout(new BorderLayout(20, 20));
		this.panel.setLayout(new GridLayout(8, 5));
		this.add(panel, BorderLayout.CENTER);
		this.add(res, BorderLayout.NORTH);
		this.setVisible(true);
		this.setSize(750, 500);
		this.res.setFont(new Font("Arial", Font.PLAIN, 40));
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		this.plotController = new PlotController();
		this.rangeVonX = -5;
		this.rangeBisX = 5;

	}

	/**
	 * Author: Maxi
	 */
	public void actionPerformed(ActionEvent e) {
		JButton source = (JButton) e.getSource();
		if (source.getText().equals("←")) {
			if (!this.res.getText().isEmpty()) {
				this.res.setText(res.getText().substring(0, this.res.getText().length() - 1));
			}
		} else if (source.getText().equals("AC")) {
			res.setText(null);

		} else if (source.getText().equals("Matrix")) {

			MatrixCalc mCalc = new MatrixCalc();

		} else if (source.getText().equals("=")) {
			try {
				if(this.res.getText().contains("x")) {
					PlotController plotController = new PlotController();
			    	plotController.plotExpression(this.res.getText(), rangeVonX, rangeBisX);
				} else {
					Parser parser = new Parser();
					res.setText(Double.toString(parser.parse(res.getText())));
				}

			} catch (PrologException exception) {
				System.out.println("fasdlfsjafklsjaf");
				exception.printStackTrace();
			}
		} else if(source.getText().equals("x^y")) {
			res.setText(res.getText() + "^");
		} else {
			res.setText(res.getText() + source.getText());

		}
	}

	/**
	 * 
	 * @Override public void actionPerformed(ActionEvent e) { JButton source =
	 *           (JButton) e.getSource(); if (source == testButton) {
	 *           this.plotController.testDaten(); } if (source == plus) { entry1();
	 *           res.setText(null); last = pl; } else if (source == minus) {
	 *           entry1(); last = mi; } else if (source == multiplication) {
	 *           entry1(); last = mu; } else if (source == division) { entry1();
	 *           last = di; } else if (source == power) { entry1(); last = po; }
	 *           else if (source == modulo) { entry1(); last = mo; } else if (source
	 *           == squareRoot) { entry1(); last = sq; } else if (source == cosinus)
	 *           { entry1(); last = cos; } else if (source == sinus) { entry1();
	 *           last = sin; } else if (source == pi) {
	 *           res.setText(String.valueOf(Math.PI)); } else if (source == euler) {
	 *           res.setText(String.valueOf(Math.E)); } else if (source == AC) {
	 *           entry1 = 0; entry2 = 0; res.setText(null); } else if (source ==
	 *           exponent) { this.x = new ArrayList<Double>(); this.x =
	 *           NumpyUtils.linspace(-Math.PI, Math.PI, 256); // Dummy Liste mit
	 *           Werten fuellen damit man eine Kurve // beim plotten bekommt
	 *           this.xPressed = true; } else if (source == exit) { System.exit(0);
	 *           } else if (source == equals) { try { if (!res.getText().equals(""))
	 *           { entry2 = Double.parseDouble(res.getText()); } else { entry2 = 0;
	 *           }
	 * 
	 * 
	 *           // Ergebnis hier: String erg = (last.execute(entry1,
	 *           entry2)).toString(); // Hier steht das Ergebnis von Prolog int
	 *           start = erg.indexOf("="); int end = erg.indexOf("}");
	 * 
	 *           this.ergebnis = Double.parseDouble(erg.substring(start + 1, end));
	 *           this.res.setText(erg.substring(start + 1, end));
	 * 
	 *           } catch (PrologException pl) { res.setText("Bruh"); } } else { for
	 *           (int i = 0; i < 10; i++) { if (source == numbers[i]) { String t =
	 *           res.getText(); t += i; res.setText(t); } } } }
	 */

	

	/**
	 * Author Berkan
	 */
	private void initTextFelder() {

		// Text zentrieren in Custom Textfield geht nicht
		this.fieldRangeVonX = new HintTextField("     Wert von X: std:-5");
		this.fieldRangeBisX = new HintTextField("     Wert bis X: std:5");

		// Felder in Panel adden
		this.panel.add(fieldRangeVonX);
		this.panel.add(fieldRangeBisX);

		// Dummy Buttons fuer UI / Zeilenumbruch
		this.panel.add(new JLabel());

		// Matrix Button
		this.matrixButton = new JButton("Matrix");
		matrixButton.addActionListener(this);

		this.panel.add(matrixButton);

		// Confirm Button
		JButton confirmButton = new JButton("OK");
		this.panel.add(confirmButton);

		// Wenn confirm Button geklickt wird, dann werden die Plot Werte: von, bis //
		// uebernommen.

		confirmButton.addActionListener(e -> {
			try {

				//System.out.println("vonX empty:" + this.fieldRangeVonX.getText().isEmpty() +
				//		" bisX empty: ");

				this.rangeVonX = Double.parseDouble(this.fieldRangeVonX.getText());
				this.rangeBisX = Double.parseDouble(this.fieldRangeBisX.getText());

				if (rangeVonX > rangeBisX) {
					throw new MinMaxException();
				}

				StringBuilder s = new StringBuilder();
				s.append("von X: ").append(rangeVonX).append(" bis X: ").append(rangeBisX).append("\n").toString();
				System.out.println(s);

			} catch (NumberFormatException nfe) {
				JOptionPane.showMessageDialog(null, " Nur Zahlen eingeben! ");
			} catch (MinMaxException mme) {
				JOptionPane.showMessageDialog(null, " VON Werte duerfen nicht groesser als BIS Werte ");
			}
		});

	}



	/**
	 * Author: Berkan
	 */
	private void initButtons() {

		this.jButtonList.add(new JButton("2^nd"));
		this.jButtonList.add(new JButton("π"));
		this.jButtonList.add(new JButton("e"));
		this.jButtonList.add(new JButton("AC"));
		this.jButtonList.add(new JButton("←"));

		this.jButtonList.add(new JButton("sin"));
		this.jButtonList.add(new JButton("cos"));
		this.jButtonList.add(new JButton("tan"));
		this.jButtonList.add(new JButton("exp"));
		this.jButtonList.add(new JButton("mod"));

		this.jButtonList.add(new JButton("sqrt"));
		this.jButtonList.add(new JButton("("));
		this.jButtonList.add(new JButton(")"));
		this.jButtonList.add(new JButton("n!"));
		this.jButtonList.add(new JButton("/"));

		this.jButtonList.add(new JButton("x^y"));
		this.jButtonList.add(new JButton("7"));
		this.jButtonList.add(new JButton("8"));
		this.jButtonList.add(new JButton("9"));
		this.jButtonList.add(new JButton("*"));

		this.jButtonList.add(new JButton("x"));
		this.jButtonList.add(new JButton("4"));
		this.jButtonList.add(new JButton("5"));
		this.jButtonList.add(new JButton("6"));
		this.jButtonList.add(new JButton("-"));

		this.jButtonList.add(new JButton("log"));
		this.jButtonList.add(new JButton("1"));
		this.jButtonList.add(new JButton("2"));
		this.jButtonList.add(new JButton("3"));
		this.jButtonList.add(new JButton("+"));

		this.jButtonList.add(new JButton("ln"));
		this.jButtonList.add(new JButton("+-"));
		this.jButtonList.add(new JButton("0"));
		this.jButtonList.add(new JButton("."));
		this.jButtonList.add(new JButton("="));

		for (JButton j : jButtonList) {
			j.setFont(new Font("Arial", Font.PLAIN, 24));
			j.addActionListener(this);
			this.panel.add(j);
		}

	}
}
