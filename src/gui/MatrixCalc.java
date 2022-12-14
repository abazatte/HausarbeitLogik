package gui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import business.MatrixAdd;
import business.MatrixMul;
import business.MatrixSub;
import business.MatrixTranspose;
import listener.MatrixHelpMenuMouseListener;
import math.Matrix;

/**
 * @author Berkan Yildiz
 */

public class MatrixCalc implements ActionListener {

    private JFrame frame;
    private JPanel panelLeft;
    private JPanel panelRight;
    private JPanel panelBottom;
    private JPanel panelCenter;

    private JMenuBar menuBar;
    private JMenu menu;
    private Matrix eingabeLinksMatrix;
    private Matrix eingabeRechtsMatrix;
    private Matrix ergebnisMatrix;

    private String operanden[] = {"+", "-", "*", "L^t", "R^t"};

    /**
     * Konstruktor der MatrixCalc JFrame
     *
     * @Author: Berkan Yildiz
     */
    public MatrixCalc() {
        this.frame = new JFrame();
        this.frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.frame.setTitle("Matrix Calc");
        this.frame.setVisible(true);
        this.frame.setLayout(new BorderLayout(5, 5));

        this.initPanels();
        this.fillPanels();
        this.initMenubar();

        this.frame.setSize(750, 500);
    }

    /**
     * Initialisiert eine Hilfsleiste oben links im View die der User auswaehlen kann.
     *
     * @author Berkan Yildiz
     */
    private void initMenubar() {
        this.menuBar = new JMenuBar();
        this.menu = new JMenu("Help");
        menu.setMnemonic(KeyEvent.VK_H);
        menu.addMouseListener(new MatrixHelpMenuMouseListener());
        menuBar.add(menu);
        this.frame.setJMenuBar(menuBar);
    }

    /**
     * Initialisert die Panels und setzt diese in das BorderLayout
     *
     * @author: Berkan Yildiz
     */
    private void initPanels() {
        this.panelLeft = new JPanel();
        this.panelLeft.setLayout(new GridLayout(4, 4));
        this.frame.add(panelLeft, BorderLayout.WEST);

        this.panelRight = new JPanel();
        this.panelRight.setLayout(new GridLayout(4, 4));
        this.frame.add(panelRight, BorderLayout.EAST);

        this.panelBottom = new JPanel();
        this.panelBottom.setLayout(new GridLayout(4, 4));
        this.frame.add(panelBottom, BorderLayout.SOUTH);

        this.panelCenter = new JPanel();
        this.panelCenter.setLayout(new GridLayout(1, 4));
        this.frame.add(panelCenter, BorderLayout.NORTH);

    }

    /**
     * Fuelltd die Panels mit JTextFields die dann beschrieben werden koennen.
     *
     * @author: Berkan Yildiz
     */
    private void fillPanels() {
        for (int i = 0; i < 16; i++) {
            JTextField tmp1 = new JTextField();
            JTextField tmp2 = new JTextField();
            JLabel tmp3 = new JLabel();

            tmp1.setFont(new Font("Arial", Font.PLAIN, 40));
            tmp2.setFont(new Font("Arial", Font.PLAIN, 40));
            tmp3.setFont(new Font("Arial", Font.PLAIN, 24));

            tmp1.setHorizontalAlignment(JTextField.CENTER);
            tmp2.setHorizontalAlignment(JTextField.CENTER);
            tmp3.setHorizontalAlignment(JTextField.CENTER);

            tmp1.setPreferredSize(new Dimension(80, 40));
            tmp2.setPreferredSize(new Dimension(80, 40));
            tmp3.setPreferredSize(new Dimension(80, 40));

            this.panelLeft.add(tmp1);
            this.panelRight.add(tmp2);
            this.panelBottom.add(tmp3);
        }

        for (String s : operanden) {
            JButton button = new JButton(s);
            button.setHorizontalAlignment(SwingConstants.CENTER);
            button.setFont(new Font("Arial", Font.PLAIN, 40));
            button.addActionListener(this);
            this.panelCenter.add(button);
        }
    }

    /**
     * EventListener fuer die angeclickten Buttons.
     *
     * @author: Berkan Yildiz
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton source = (JButton) e.getSource();
        if (source.getText().equals("+")) {
            JOptionPane.showMessageDialog(null, " L + R");
        } else if (source.getText().equals("-")) {
            JOptionPane.showMessageDialog(null, "L - R");
        } else if (source.getText().equals("*")) {
            JOptionPane.showMessageDialog(null, " L * R");
        } else if (source.getText().equals("L^t")) {
            JOptionPane.showMessageDialog(null, " Linke Matrix transponieren");
        } else if (source.getText().equals("R^t")) {
            JOptionPane.showMessageDialog(null, " Rechte Matrix transponieren");
        }

        this.initLeftMatrix();
        this.initRightMatrix();
        System.out.println("Operand ist : " + source.getText());
        this.rechneMatrix(source.getText());
    }

    /**
     * Initialisiert die linke Eingabematrix
     * (Code duplizierung aber klare unterscheidung wegen linker/rechter Matrix)
     *
     * Author: Berkan Yildiz
     */
    private void initLeftMatrix() {

        Component[] components = this.panelLeft.getComponents();
        List<Double> werte = new ArrayList<>();

        for (Component c : components) {
            JTextField inhalt = (JTextField) c;
            if (inhalt.getText().isEmpty()) {
                inhalt.setText("0");
            }
            werte.add(Double.parseDouble(inhalt.getText()));
        }

        this.eingabeLinksMatrix = new Matrix(werte.get(0).floatValue(), werte.get(1).floatValue(),
                        werte.get(2).floatValue(), werte.get(3).floatValue(), werte.get(4).floatValue(),
                        werte.get(5).floatValue(), werte.get(6).floatValue(), werte.get(7).floatValue(),
                        werte.get(8).floatValue(), werte.get(9).floatValue(), werte.get(10).floatValue(),
                        werte.get(11).floatValue(), werte.get(12).floatValue(), werte.get(13).floatValue(),
                        werte.get(14).floatValue(), werte.get(15).floatValue());

        System.out.println("Eingabe links ist: ");
        System.out.println(eingabeLinksMatrix);
        System.out.println("-------------------");
    }

    /**
     * Initialisiert die rechte Eingabematrix
     * (Code duplizierung aber klare unterscheidung wegen linker/rechter Matrix)
     *
     * @author: Berkan
     */
    private void initRightMatrix() {
        Component[] components = this.panelRight.getComponents();
        List<Double> werte = new ArrayList<>();

        for (Component c : components) {
            JTextField inhalt = (JTextField) c;
            if (inhalt.getText().isEmpty()) {
                inhalt.setText("0");
            }
            werte.add(Double.parseDouble(inhalt.getText()));
        }

        this.eingabeRechtsMatrix = new Matrix(werte.get(0).floatValue(), werte.get(1).floatValue(),
                werte.get(2).floatValue(), werte.get(3).floatValue(), werte.get(4).floatValue(),
                werte.get(5).floatValue(), werte.get(6).floatValue(), werte.get(7).floatValue(),
                werte.get(8).floatValue(), werte.get(9).floatValue(), werte.get(10).floatValue(),
                werte.get(11).floatValue(), werte.get(12).floatValue(), werte.get(13).floatValue(),
                werte.get(14).floatValue(), werte.get(15).floatValue());

        System.out.println("Eingabe rechts ist: ");
        System.out.println(eingabeRechtsMatrix);
        System.out.println("-------------------");
    }

    /**
     * Errechnet die Ergebnismatrix, abhaengig vom ausgewaehlten Operanden
     *
     * @author: Berkan Yildiz
     *
     * @param operand (+,-,*,t)
     */
    private void rechneMatrix(String operand) {
        List<Double> werte = new ArrayList<>();

        switch (operand) {
            case "+" -> {
                String erg = new MatrixAdd().execute(eingabeLinksMatrix, eingabeRechtsMatrix);
                werte = this.getWerteVonErgebnis(erg);
            }
            case "-" -> {
                String erg = new MatrixSub().execute(eingabeLinksMatrix, eingabeRechtsMatrix);
                werte = this.getWerteVonErgebnis(erg);
            }
            case "*" -> {
                String erg = new MatrixMul().execute(eingabeLinksMatrix, eingabeRechtsMatrix);
                werte = this.getWerteVonErgebnis(erg);

            }
            case "L^t" -> {
                String erg = new MatrixTranspose().execute(eingabeLinksMatrix, null);
                werte = this.getWerteVonErgebnis(erg);
            }

            case "R^t" -> {
                String erg = new MatrixTranspose().execute(eingabeRechtsMatrix, null);
                werte = this.getWerteVonErgebnis(erg);
            }
        }
        this.ergebnisMatrix = new Matrix(werte.get(0).floatValue(), werte.get(1).floatValue(),
                werte.get(2).floatValue(), werte.get(3).floatValue(), werte.get(4).floatValue(),
                werte.get(5).floatValue(), werte.get(6).floatValue(), werte.get(7).floatValue(),
                werte.get(8).floatValue(), werte.get(9).floatValue(), werte.get(10).floatValue(),
                werte.get(11).floatValue(), werte.get(12).floatValue(), werte.get(13).floatValue(),
                werte.get(14).floatValue(), werte.get(15).floatValue());

        System.out.println(ergebnisMatrix);
        this.printMatrixToJFrame(werte);
    }

    /**
     * @author: Berkan Yildiz
     *
     * @param ausdruck der zu evaluieren ist
     * @return eine Liste von Doubles die aus dem String "zerflueckt" worden sind
     * <p>
     * Refer to <a href="https://stackoverflow.com/questions/9381560/parse-multiple-doubles-from-a-string">Parse multiple doubles from a String</a>
     */
    private ArrayList<Double> getWerteVonErgebnis(String ausdruck) {
        ArrayList<Double> tmp = new ArrayList<Double>();

        // Regulaerer Ausdruck hat ein / aber bei Java macht man //
        Matcher matcher = Pattern.compile("[-+]?\\d*\\.?\\d+([eE][-+]?\\d+)?").matcher(ausdruck);
        while (matcher.find()) {
            double element = Double.parseDouble(matcher.group());
            tmp.add(element);
        }
        return tmp;
    }

    private void printMatrixToJFrame(List<Double> ergWerte) {
        Component[] components = this.panelBottom.getComponents();
        for (int i = 0; i < components.length; i++) {
            JLabel inhalt = (JLabel) components[i];
            inhalt.setText(Double.toString(ergWerte.get(i)));
        }
    }
}
