package gui;

import business.*;
import org.jpl7.PrologException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Interface extends JFrame implements ActionListener {
    private JButton plus, minus, modulo, division, power, multiplication, squareRoot, fak, equals, AC, exit, cosinus, sinus, pi, platzhalter;
    private JButton numbers[];
    private JTextField eingabe;
    private JPanel panel;

    private Command pl, mi, mo, di, po, mu, sq, f, cos, sin;
    private Command last;

    private double entry1;
    private double entry2;

    public Interface() {
        super("Prolog Calculator");
        this.numbers = new JButton[10];
        this.eingabe = new JTextField();
        panel = new JPanel();
        initButtons();
        initCommands();

        setLayout(new BorderLayout());
        panel.setLayout(new GridLayout(6, 6));
        add(panel, BorderLayout.CENTER);
        add(eingabe, BorderLayout.NORTH);
        setVisible(true);
        setSize(750, 500);
        eingabe.setFont(new Font("Arial", Font.PLAIN, 40));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        /*JButton source = (JButton) e.getSource();
        if (source == plus) {
            eingabe.setText(eingabe.getText() + source.getText());
            last = pl;
        } else if (source == minus) {
        	eingabe.setText(eingabe.getText() + source.getText());
            last = mi;
        } else if (source == multiplication) {
        	eingabe.setText(eingabe.getText() + source.getText());
            last = mu;
            
        } else if (source == division) {
        	eingabe.setText(eingabe.getText() + source.getText());
            last = di;
        } else if (source == power) {
        	eingabe.setText(eingabe.getText() + source.getText());
            last = po;
        } else if (source == modulo) {
        	eingabe.setText(eingabe.getText() + source.getText());
            last = mo;
        } else if (source == squareRoot) {
        	eingabe.setText(eingabe.getText() + source.getText());
            last = sq;
            
        } else if(source == cosinus) {
        	eingabe.setText(eingabe.getText() + source.getText());
            last = cos;
        } else if(source == sinus) {
        	eingabe.setText(eingabe.getText() + source.getText());
            last = sin;
        } else if(source == pi) {
        	//pi in Prolog????
            eingabe.setText(String.valueOf(Math.PI));
        } else if (source == AC) {
            entry1 = 0;
            entry2 = 0;
            eingabe.setText(null);
        } else if (source == exit) {
            System.exit(0);
        } else if (source == equals) {
            try {
                if (!eingabe.getText().equals(""))
                    this.entry2 = Double.parseDouble(eingabe.getText());
                else entry2 = 0;
                eingabe.setText(last.execute(entry1, entry2).toString());
            } catch(PrologException pl) {
                eingabe.setText("BIST DU DUMM ODER SO???");
            }
        } else {
            for (int i = 0; i < 10; i++) {
                if (source == numbers[i]) {
                    String t = eingabe.getText();
                    t += i;
                    eingabe.setText(t);
                }
            }
        }*/
    	JButton source = (JButton) e.getSource();
        if(source == exit) {
            System.exit(0);
        }else if(source == AC) {
            eingabe.setText(null);
        } else if(source == equals) {
        	try {
                if (!eingabe.getText().equals(""))
                    this.entry2 = Double.parseDouble(eingabe.getText());
                else entry2 = 0;
                eingabe.setText(last.execute(entry1, entry2).toString());
            } catch(PrologException pl) {
                eingabe.setText("BIST DU DUMM ODER SO???");
            }
        } 
        else {
            eingabe.setText(eingabe.getText() + source.getText());
        }
    }

    private void initCommands() {
        pl = new Plus();
        mu = new Multiplication();
        mo = new Modulo();
        di = new Division();
        cos = new Cosinus();
        sin = new Sinus();
        po = new Power();
        sq = new SquareRoot();
        mi = new Minus();
    }

    private void initButtons() {
    	
    	this.modulo = new JButton("%");
        this.modulo.addActionListener(this);
        this.modulo.setFont(new Font("Arial", Font.PLAIN, 40));
        this.panel.add(modulo);
        
        this.sinus = new JButton("sin");
        this.sinus.addActionListener(this);
        this.sinus.setFont(new Font("Arial", Font.PLAIN, 40));
        this.panel.add(sinus);
    	
        this.cosinus = new JButton("cos");
        this.cosinus.addActionListener(this);
        this.cosinus.setFont(new Font("Arial", Font.PLAIN, 40));
        this.panel.add(cosinus);
        
        this.exit = new JButton("off");
        this.exit.addActionListener(this);
        this.exit.setFont(new Font("Arial", Font.PLAIN, 40));
        this.panel.add(exit);

        this.AC = new JButton("AC");
        this.AC.addActionListener(this);
        this.AC.setFont(new Font("Arial", Font.PLAIN, 40));
        this.panel.add(AC);
        
        this.power = new JButton("^");
        this.power.addActionListener(this);
        this.power.setFont(new Font("Arial", Font.PLAIN, 40));
        this.panel.add(power);
        
        this.squareRoot = new JButton("√");
        this.squareRoot.addActionListener(this);
        this.squareRoot.setFont(new Font("Arial", Font.PLAIN, 40));
        this.panel.add(squareRoot);
        
        this.division = new JButton("/");
        this.division.addActionListener(this);
        this.division.setFont(new Font("Arial", Font.PLAIN, 40));
        this.panel.add(division);
        
    	for (int i = 7; i <= 9; i++) {
            numbers[i] = new JButton(i + "");
            numbers[i].setFont(new Font("Arial", Font.PLAIN, 40));
            panel.add(numbers[i]);
            numbers[i].addActionListener(this);
        }
    	
        this.multiplication = new JButton("*");
        this.multiplication.addActionListener(this);
        this.multiplication.setFont(new Font("Arial", Font.PLAIN, 40));
        this.panel.add(multiplication);

    	for (int i = 4; i <= 6; i++) {
            numbers[i] = new JButton(i + "");
            numbers[i].setFont(new Font("Arial", Font.PLAIN, 40));
            panel.add(numbers[i]);
            numbers[i].addActionListener(this);
        }
        
        this.minus = new JButton("-");
        this.minus.addActionListener(this);
        this.minus.setFont(new Font("Arial", Font.PLAIN, 40));
        this.panel.add(minus);
    	
    	for (int i = 1; i <= 3; i++) {
            numbers[i] = new JButton(i + "");
            numbers[i].setFont(new Font("Arial", Font.PLAIN, 40));
            panel.add(numbers[i]);
            numbers[i].addActionListener(this);
        }
        
        this.plus = new JButton("+");
        this.plus.addActionListener(this);
        this.plus.setFont(new Font("Arial", Font.PLAIN, 40));
        this.panel.add(plus);

        this.platzhalter = new JButton();
        this.panel.add(this.platzhalter);

        numbers[0] = new JButton(0 + "");
        numbers[0].setFont(new Font("Arial", Font.PLAIN, 40));
        panel.add(numbers[0]);
        numbers[0].addActionListener(this);

        this.pi = new JButton("π");
        this.pi.addActionListener(this);
        this.pi.setFont(new Font("Arial", Font.PLAIN, 40));
        this.panel.add(pi);

        this.equals = new JButton("=");
        this.equals.addActionListener(this);
        this.equals.setFont(new Font("Arial", Font.PLAIN, 40));
        this.panel.add(equals);

    }

    private void entry1() {
        this.entry1 = Double.parseDouble(eingabe.getText());
        eingabe.setText(null);
    }
}
