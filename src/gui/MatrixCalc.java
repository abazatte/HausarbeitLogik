package gui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import math.Matrix;

public class MatrixCalc implements ActionListener {

	private JFrame frame;
	private JPanel panelLeft;
	private JPanel panelRight;
	private JPanel panelBottom;
	private JPanel panelCenter;
	
	private Matrix eingabeLinksMatrix;
	private Matrix eingabeRechtsMatrix;
	private Matrix ergebnisMatrix;
	
	private String operanden[] = {"+", "-", "*", "/"};
	
	
	public MatrixCalc() {
		this.frame = new JFrame();
		this.frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.frame.setTitle("Matrix Calc");
		this.frame.setVisible(true);
		this.frame.setLayout(new BorderLayout(20,20));
		this.frame.setSize(750, 500);
		

		this.initPanels();
		this.fillPanels();
		
		
		
		
	}
	
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
		this.panelCenter.setLayout(new GridLayout(4, 1));
		this.frame.add(panelCenter, BorderLayout.CENTER);

	}
	
	private void fillPanels() {
		
		// Scuffed aber von logician ok
		for(int i = 0; i < 16; i++) {
			JTextField tmp1 = new JTextField();
			JTextField tmp2 = new JTextField();
			JTextField tmp3 = new JTextField();
			
			tmp1.setFont(new Font("Arial", Font.PLAIN, 40));
			tmp2.setFont(new Font("Arial", Font.PLAIN, 40));
			tmp3.setFont(new Font("Arial", Font.PLAIN, 40));
			
			tmp1.setHorizontalAlignment(JTextField.CENTER);
			tmp2.setHorizontalAlignment(JTextField.CENTER);
			tmp3.setHorizontalAlignment(JTextField.CENTER);
			
			tmp1.setText("" + i);
			tmp2.setText("" + i);
			tmp3.setText("" + i);
			this.panelLeft.add(tmp1);
			this.panelRight.add(tmp2);
			this.panelBottom.add(tmp3);
		}
		
		for(int i = 0 ; i < operanden.length; i++) {
			JButton button = new JButton(operanden[i]);
			button.setHorizontalAlignment(SwingConstants.CENTER);
			button.setFont(new Font("Arial", Font.PLAIN, 40));
			button.addActionListener(this);
			this.panelCenter.add(button);
			
		}
		
		
	}
	
	

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton source = (JButton) e.getSource();
		if(source.getText().equals("+")) {
			JOptionPane.showMessageDialog(null, " + gedrueckt");
		} else if(source.getText().equals("-")) {
			JOptionPane.showMessageDialog(null, " - gedrueckt");
		} else if(source.getText().equals("*")) {
			JOptionPane.showMessageDialog(null, " * gedrueckt");
		} else if(source.getText().equals("/")) {
			JOptionPane.showMessageDialog(null, " / gedrueckt");
		}

	}
	
	
	private void initLeftMatrix() {
		
		this.panelLeft.getComponent(0);
		
		this.eingabeLinksMatrix = new Matrix
				(0, 0, 0, 0,
				0, 0, 0, 0,
				0, 0, 0, 0,
				0, 0, 0, 0);
	}
	
	private void initRightMatrix() {
		
	}

}
