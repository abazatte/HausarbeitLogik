package gui;

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
		// 9 zeilen, 5 spalten
		this.panel.setLayout(new GridLayout(9, 5));
		this.add(panel, BorderLayout.CENTER);
	}

	/**
	 * Author: Maxi
	 */
	public void actionPerformed(ActionEvent e) {
		JButton source = (JButton) e.getSource();
		if (source.getText().equals("←")) {
			moveCaretLeft();
		} else if (source.getText().equals("→")) {
			moveCaretRight();
		} else if (source.getText().equals("DEL")) {
			deleteCharacter();
		} else if (source.getText().equals("AC")) {
			eingabeTextField.setText(null);
		} else if (source.getText().equals("Matrix")) {
			MatrixCalc mCalc = new MatrixCalc();
		} else if (source.getText().equals("=")) {
			evaluateExpression();
		} else if (source.getText().equals("OK")) {
			updatePlotXValues();
		} else if (source.getText().equals("x^y")) {
			insertStringIntoEingabe("^");
		} else if (source.getText().equals("update Round")) {
			updateRoundNachkommastellen();
		} else if (source.getText().equals("sin") || source.getText().equals("cos") || source.getText().equals("tan")
				|| source.getText().equals("fak") || source.getText().equals("sqrt") || source.getText().equals("ln")
				|| source.getText().equals("round")) {
			insertStringIntoEingabe(source.getText() + "(");
		} else {
			// normale Zahlen und symbole
			insertStringIntoEingabe(source.getText());
		}
		// nachdem auf ein Button geklickt wird, geht der Focus verloren
		// er muss manuell wiederhergestellt werden
		this.eingabeTextField.requestFocus();

	}

	/**
	 * Wenn der updateRound Button gedrückt wird, muss der interne Wert aktualisiert
	 * werden
	 * 
	 * @author Maximilian
	 */
	private void updateRoundNachkommastellen() {
		try {
			this.nachkommaStellen = Integer.parseInt(this.fieldRound.getText());
			JOptionPane.showMessageDialog(null, "Runden auf: " + this.nachkommaStellen + " Nachkommastellen");
		} catch (NumberFormatException nfe) {
			JOptionPane.showMessageDialog(null, " Nur Zahlen eingeben! ");
		}
	}

	/**
	 * Wenn confirm Button geklickt wird, dann werden die Plot Werte: von X , bis X
	 * uebernommen.
	 * 
	 * @author Berkan
	 */
	private void updatePlotXValues() {
		//
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
	}

	/**
	 * hier wird die Prolog schnittstelle aufgerufen, und falls ein X in der eingabe
	 * vorhanden ist, geplottet
	 * 
	 * @author Maximilian
	 */
	private void evaluateExpression() {
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
	}

	/**
	 * löscht den Character an der Stelle des Eingabesymbols, und bewegt das
	 * Eingabesymbol nach links
	 * 
	 * @author Maximilian
	 */
	private void deleteCharacter() {
		int cPos = this.eingabeTextField.getCaretPosition();
		int textLength = this.eingabeTextField.getText().length();
		if (cPos == textLength) {
			this.eingabeTextField.setText(this.eingabeTextField.getText().substring(0, textLength - 1));
			this.eingabeTextField.setCaretPosition(cPos - 1);
		} else if (cPos > 0 && cPos < textLength) {
			String beforeString = this.eingabeTextField.getText().substring(0, cPos - 1);
			String afterString = this.eingabeTextField.getText().substring(cPos, textLength);
			this.eingabeTextField.setText(beforeString + afterString);
			this.eingabeTextField.setCaretPosition(cPos - 1);
		}
		// um unvorhergesehenes Verhalten zu vermeiden, wird die Caret Position nur in
		// den if Bedingugnen verändert, auch wenn es wiederholter code ist

	}

	/**
	 * bewegt das Eingabesymbol nach rechts, außer wenn es schon ganz rechts ist
	 * 
	 * @author Maximilian
	 */
	private void moveCaretRight() {
		int cPos = this.eingabeTextField.getCaretPosition();
		if (cPos < this.eingabeTextField.getText().length()) {
			this.eingabeTextField.setCaretPosition(cPos + 1);
		}
	}

	/**
	 * bewegt das Eingabesymbol nach links, außer wenn es schon ganz links ist
	 * 
	 * @author Maximilian
	 */
	private void moveCaretLeft() {
		int cPos = this.eingabeTextField.getCaretPosition();
		if (cPos > 0) {
			this.eingabeTextField.setCaretPosition(cPos - 1);
		}
	}

	/**
	 * <p>
	 * fügt einen String in die Eingabe des Taschenrechners ein, und bewegt das
	 * Einfügesymbol bzw Caret um die Länge des Strings weiter
	 * </p>
	 * 
	 * @author Maximilian
	 * @param toInsert
	 */
	private void insertStringIntoEingabe(String toInsert) {
		int cPos = this.eingabeTextField.getCaretPosition();
		int textLength = this.eingabeTextField.getText().length();
		if (cPos == textLength) {
			this.eingabeTextField.setText(eingabeTextField.getText() + toInsert);
			this.eingabeTextField.setCaretPosition(cPos + toInsert.length());
		} else if (cPos == 0) {
			this.eingabeTextField.setText(toInsert + eingabeTextField.getText());
			this.eingabeTextField.setCaretPosition(cPos + toInsert.length());
		} else if (cPos > 0 && cPos < textLength) {
			String beforeString = this.eingabeTextField.getText().substring(0, cPos);
			String afterString = this.eingabeTextField.getText().substring(cPos, textLength);
			this.eingabeTextField.setText(beforeString + toInsert + afterString);
			this.eingabeTextField.setCaretPosition(cPos + toInsert.length());
		}
		// um unvorhergesehenes Verhalten zu vermeiden, wird die Caret Position nur in
		// den if Bedingugnen verändert, auch wenn es wiederholter code ist
	}

	/**
	 * @author Maximilian
	 */
	private void initTextFelderUndButtonsRound() {

		// Dummy label
		this.panel.add(new JLabel());

		this.fieldRound = new HintTextField("precision: 5");
		this.fieldRound.setFont(new Font("Arial", Font.PLAIN, 16));
		this.fieldRound.setHorizontalAlignment(JTextField.CENTER);
		this.panel.add(fieldRound);

		this.panel.add(new JLabel());
		this.panel.add(new JLabel());

		JButton updateNachkommaButton = new JButton("update Round");
		updateNachkommaButton.addActionListener(this);
		this.panel.add(updateNachkommaButton);
	}

	/**
	 * Author Berkan, Maximilian
	 */
	private void initTextFelderUndButtonsMatrix() {
		// init HintTextField
		this.fieldRangeVonX = new HintTextField("Wert von X: std:-5");
		this.fieldRangeBisX = new HintTextField("Wert bis X: std:5");
		// Text zentrieren 
		this.fieldRangeVonX.setHorizontalAlignment(JTextField.CENTER);
		this.fieldRangeBisX.setHorizontalAlignment(JTextField.CENTER);
		
		this.fieldRangeVonX.setFont(new Font("Arial", Font.PLAIN, 16));
		this.fieldRangeBisX.setFont(new Font("Arial", Font.PLAIN, 16));

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
	 * fügt die Buttons des Taschenrechners in der richtigen Reihenfolge hinzu und
	 * setzt deren Listener.
	 * 
	 * @author max Berkan
	 * 
	 */
	private void initButtons() {
		List<JButton> jButtonList = new ArrayList<>();

		jButtonList.add(new JButton("π"));
		jButtonList.add(new JButton("←"));
		jButtonList.add(new JButton("→"));
		jButtonList.add(new JButton("AC"));
		jButtonList.add(new JButton("DEL"));

		jButtonList.add(new JButton("e"));
		jButtonList.add(new JButton("sin"));
		jButtonList.add(new JButton("cos"));
		jButtonList.add(new JButton("tan"));
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

		jButtonList.add(new JButton("x^2"));
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
