package gui;

import business.*;
import exception.MinMaxException;
import helper.Parser;
import listener.CalculatorHelpMenuMouseListener;

import org.jpl7.PrologException;

import Plotter.PlotController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.nio.channels.NonWritableChannelException;
import java.util.ArrayList;
import java.util.List;

public class Interface extends JFrame implements ActionListener {

	private JTextField fieldRangeVonX;
	private JTextField fieldRangeBisX;
	private double rangeVonX;
	private double rangeBisX;

	
	private JTextField fieldRound;
	private int nachkommaStellen;
	
	private JTextField eingabeTextField;
	private JPanel panel;

	/**
	 * @author Berkan Yildiz, Maximilian Jaesch
	 *         <p>
	 *         </p>
	 *         <a href=
	 *         "https://docs.oracle.com/javase/tutorial/uiswing/components/menu.html">
	 *         Quelle Menu </a>
	 */
	public Interface() {
		super("Prolog Calculator");
		this.setLayout(new BorderLayout(20, 20));
		this.initPanel();
		this.initEingabeTextField();
		this.initButtons();
		this.initTextFelderUndButtonsRound();
		this.initTextFelderUndButtonsMatrix();
		this.initHelpMenu();

		// Plot werte initialisieren
		this.rangeVonX = -5;
		this.rangeBisX = 5;

		// nachkomma runden initialisieren
		this.nachkommaStellen = 5;
		
		// set Size am ende, damit die Help leiste ordentlich angezeigt wird
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(750, 700);
		this.setVisible(true);
	}

	private void initHelpMenu() {
		// initMenu
		JMenuBar menuBar = new JMenuBar();
		JMenu menu = new JMenu("Help");
		menu.setMnemonic(KeyEvent.VK_H);
		menu.addMouseListener(new CalculatorHelpMenuMouseListener());
		menuBar.add(menu);
		this.setJMenuBar(menuBar);
	}

	private void initEingabeTextField() {
		// initEingabeTextField
		this.eingabeTextField = new JTextField(13);
		this.eingabeTextField.setFont(new Font("Arial", Font.PLAIN, 40));
		this.add(eingabeTextField, BorderLayout.NORTH);
	}

	private void initPanel() {
		// initPanel
		this.panel = new JPanel();
		//9 zeilen, 5 spalten
		this.panel.setLayout(new GridLayout(9, 5));
		this.add(panel, BorderLayout.CENTER);
	}

	/**
	 * Author: Maxi
	 */
	public void actionPerformed(ActionEvent e) {
		JButton source = (JButton) e.getSource();
		if (source.getText().equals("←")) {
			if (!this.eingabeTextField.getText().isEmpty()) {
				this.eingabeTextField
						.setText(eingabeTextField.getText().substring(0, this.eingabeTextField.getText().length() - 1));
			}
		} else if (source.getText().equals("AC")) {
			eingabeTextField.setText(null);
		} else if (source.getText().equals("Matrix")) {
			MatrixCalc mCalc = new MatrixCalc();
		} else if (source.getText().equals("=")) {
			String expression = this.eingabeTextField.getText();
			Parser parser = new Parser(nachkommaStellen);
			expression = parser.mathKonstantenErsetzen(expression);
			try {
				if (this.eingabeTextField.getText().contains("x")) {
					PlotController plotController = new PlotController();
					plotController.plotExpression(expression, rangeVonX, rangeBisX);
				} else {
					eingabeTextField.setText(Double.toString(parser.parse(expression)));
				}

			} catch (PrologException exception) {
				JOptionPane.showMessageDialog(null, "Prolog EXCEPTION");
				exception.printStackTrace();
			} catch (RuntimeException eggception) {
				eggception.printStackTrace();
				JOptionPane.showMessageDialog(null, "Syntax ERROR");
			}
		} else if (source.getText().equals("OK")) {
			// Wenn confirm Button geklickt wird, dann werden die Plot Werte: von, bis //
			// uebernommen.
			try {
				this.rangeVonX = Double.parseDouble(this.fieldRangeVonX.getText());
				this.rangeBisX = Double.parseDouble(this.fieldRangeBisX.getText());
				if (rangeVonX > rangeBisX) {
					throw new MinMaxException();
				}
				StringBuilder s = new StringBuilder();
				s.append("von X: ").append(rangeVonX).append(" bis X: ").append(rangeBisX).append("\n").toString();
				JOptionPane.showMessageDialog(null, s);
			} catch (NumberFormatException nfe) {
				JOptionPane.showMessageDialog(null, " Nur Zahlen eingeben! ");
			} catch (MinMaxException mme) {
				JOptionPane.showMessageDialog(null, " VON Werte duerfen nicht groesser als BIS Werte ");
			}
		} else if (source.getText().equals("x^y")) {
			eingabeTextField.setText(eingabeTextField.getText() + "^");
		} else if (source.getText().equals("update Round")) {
			try {
				this.nachkommaStellen = Integer.parseInt(this.fieldRound.getText());
				JOptionPane.showMessageDialog(null, "Runden auf: " + this.nachkommaStellen + " Nachkommastellen");
			} catch (NumberFormatException nfe) {
				JOptionPane.showMessageDialog(null, " Nur Zahlen eingeben! ");
			}	
		} 
		else {
			// normale Zahlen
			eingabeTextField.setText(eingabeTextField.getText() + source.getText());
		}
	}
	/**
	 * @author Maximilian
	 */
	private void initTextFelderUndButtonsRound() {
		
		
		// Dummy label
		this.panel.add(new JLabel());
		
		
		this.fieldRound = new HintTextField("   nachkomma-Stellen: std:5");
		this.panel.add(fieldRound);
		
		this.panel.add(new JLabel());
		this.panel.add(new JLabel());
		
		JButton updateNachkommaButton = new JButton("update Round");
		updateNachkommaButton.addActionListener(this);
		this.panel.add(updateNachkommaButton);
	}
	

	/**
	 * Author Berkan,Maximilian
	 */
	private void initTextFelderUndButtonsMatrix() {
		// Text zentrieren in Custom Textfield geht nicht
		this.fieldRangeVonX = new HintTextField("     Wert von X: std:-5");
		this.fieldRangeBisX = new HintTextField("     Wert bis X: std:5");

		// Felder in Panel adden
		this.panel.add(fieldRangeVonX);
		this.panel.add(fieldRangeBisX);

		// Dummy label
		this.panel.add(new JLabel());

		// Matrix Button
		JButton matrixButton = new JButton("Matrix");
		matrixButton.addActionListener(this);
		this.panel.add(matrixButton);

		// Confirm Button
		JButton confirmButton = new JButton("OK");
		confirmButton.addActionListener(this);
		this.panel.add(confirmButton);
	}

	/**
	 * Author: Berkan
	 */
	private void initButtons() {
		List<JButton> jButtonList = new ArrayList<>();

		jButtonList.add(new JButton("2^nd"));
		jButtonList.add(new JButton("π"));
		jButtonList.add(new JButton("e"));
		jButtonList.add(new JButton("AC"));
		jButtonList.add(new JButton("←"));

		jButtonList.add(new JButton("sin"));
		jButtonList.add(new JButton("cos"));
		jButtonList.add(new JButton("tan"));
		jButtonList.add(new JButton());
		jButtonList.add(new JButton("%"));

		jButtonList.add(new JButton("sqrt"));
		jButtonList.add(new JButton("("));
		jButtonList.add(new JButton(")"));
		jButtonList.add(new JButton("fak"));
		jButtonList.add(new JButton("/"));

		jButtonList.add(new JButton("x^y"));
		jButtonList.add(new JButton("7"));
		jButtonList.add(new JButton("8"));
		jButtonList.add(new JButton("9"));
		jButtonList.add(new JButton("*"));

		jButtonList.add(new JButton("x"));
		jButtonList.add(new JButton("4"));
		jButtonList.add(new JButton("5"));
		jButtonList.add(new JButton("6"));
		jButtonList.add(new JButton("-"));

		jButtonList.add(new JButton("log"));
		jButtonList.add(new JButton("1"));
		jButtonList.add(new JButton("2"));
		jButtonList.add(new JButton("3"));
		jButtonList.add(new JButton("+"));

		jButtonList.add(new JButton("ln"));
		jButtonList.add(new JButton("round"));
		jButtonList.add(new JButton("0"));
		jButtonList.add(new JButton("."));
		jButtonList.add(new JButton("="));

		for (JButton j : jButtonList) {
			j.setFont(new Font("Arial", Font.PLAIN, 24));
			j.addActionListener(this);
			this.panel.add(j);
		}

	}
}
